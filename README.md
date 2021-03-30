# Modern MVI
##### This project contains a library that helps implement the MVI design pattern in an android application and examples of how to use the library.

### 1. What is MVI in general?

![mvi chart](https://github.com/clooss95/modern_mvi/blob/master/images/mvi_chart.png?raw=true)
### MVI stands for Model-View-Intent. MVI is one of the newest architecture patterns for Android

MVI works in a very different way compared to its distant relatives, MVC, MVP or MVVM. The role of each MVI components is as follows:

- Model represents a state. Models in MVI should be immutable to ensure a unidirectional data flow between them and the other layers in your architecture.
- Views collect intents emitted by the user and render state on the screen.
- Intent represents an intention or a desire to perform an action, either by the user or the app itself. For every action, a View receives an Intent.

### 2. How the MVI works with this library?
This library contains code that makes it easy for us to implement the MVI pattern and reduce boiler plate.
The implementation is based on several basic components:
- ViewState - state of a given screen
- ViewEffect - effect that can occur on a given screen (f.e. showing a snackbar or navigation action)
- Intent - an intention or a desire to perform an action (f.e. clicking a button or typing text)
- Presenter - object that observes the Intents and maps thems into PartialStates
- PartialState - object that updates the ViewState using "reduce" method
- MviActivity - activity class containing boiler plate code required by MVI flow
- MviFragment - activity class containing boiler plate code required by MVI flow

### Let's try it on an example

Suppose we are to implement a counter screen. This screen displays the current counter status starting from 0.
The user has the possibility to click the "increase" button, which increases the counter value by 1, or "decrease" button, which decreases the counter value by 1. The user can also press the navigation button which will take him to the next screen. Sounds simple, isn't it?

![sample screenshot](https://github.com/clooss95/modern_mvi/blob/master/images/screenshot1.png?raw=true)

### Let's move on to the code

On this screen the only state that changes is the value of the counter, so the viewstate for this screen will look like this:
```kotlin
data class CounterViewState(
    val counterValue: Int = 0
) : ViewState {
    val counterValueText: String = "$counterValue"
}
```
Then, on this screen, the only effect that can occur is navigation to the next screen, so the viewEffect will look like this (let's make it sealed class to make sure that we have handled all cases in the view implementation)
```kotlin
sealed class CounterViewEffect : ViewEffect {
    object NavigateToSecondScreen : CounterViewEffect()
}
```
Now let's take a look at the actions the user can perform. The user can click the "increase" button, click the "decrease" button or navigate to the next screen. Let's create corresponding intents (it also should be a sealed class to cover all the cases in presenter mapping method): 
```kotlin
sealed class CounterIntent : Intent {
    object Increase : CounterIntent()
    object Decrease : CounterIntent()
    object NavigateToSecondScreen : CounterIntent()
}
```
Then, we need to implement partial state classes that will know how to modify the viewstate depending on the case: 
```kotlin
sealed class CounterPartialState : PartialState<CounterViewState, CounterViewEffect> {
    object Increase : CounterPartialState() {
        override fun reduce(previousState: CounterViewState): CounterViewState {
            return previousState.copy(counterValue = previousState.counterValue + 1)
        }
    }

    object Decrease : CounterPartialState() {
        override fun reduce(previousState: CounterViewState): CounterViewState {
            return previousState.copy(counterValue = previousState.counterValue - 1)
        }
    }

    object NavigateToSecondScreen : CounterPartialState() {
        override fun mapToViewEffect(): CounterViewEffect {
            return CounterViewEffect.NavigateToSecondScreen
        }
    }
}
```
Now, we create a presenter that maps intents to partial states:
```kotlin
class CounterPresenter @Inject constructor(
    @Named(MAIN_THREAD) mainThread: Scheduler
) : Presenter<CounterViewState, CounterView, CounterPartialState, CounterIntent, CounterViewEffect>(mainThread) {
    override val defaultViewState: CounterViewState
        get() = CounterViewState()

    override fun intentToPartialState(intent: CounterIntent): Observable<CounterPartialState> =
        when (intent) {
            is CounterIntent.Increase -> Observable.just(CounterPartialState.Increase)
            is CounterIntent.Decrease -> Observable.just(CounterPartialState.Decrease)
            is CounterIntent.NavigateToSecondScreen -> Observable.just(CounterPartialState.NavigateToSecondScreen)
        }
}
```
Now let's go to the view. We assign the values of the viewstate to the widgets using databinding:

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <variable
            name="viewState"
            type="com.bonacode.modernmvi.sample.presentation.feature.counter.CounterViewState" />
    </data>


    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        android:orientation="vertical">

        <androidx.appcompat.widget.AppCompatTextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="32dp"
            android:gravity="center"
            android:text="@{viewState.counterValueText}"
            android:textColor="@color/colorBlack"
            android:textSize="80sp"
            tools:text="43" />

        <com.google.android.material.button.MaterialButton
            android:id="@+id/increaseButton"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Increase"
            android:textSize="50sp" />

        <com.google.android.material.button.MaterialButton
            android:id="@+id/decreaseButton"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Decrease"
            android:textSize="50sp" />
    </LinearLayout>
     
</layout>
```
The last step is to implement the activity/framgent class, which will collect the intents and render the state to the screen:
```kotlin
class CounterActivity :
    MviActivity<CounterViewState, CounterViewEffect, CounterView, CounterPresenter, ActivityCounterBinding>(),
    CounterView {

    override val binding: ActivityCounterBinding by viewBinding(ActivityCounterBinding::inflate)
    override val presenter: CounterPresenter by viewModels()
    override fun getMviView(): CounterView = this

    override fun render(viewState: CounterViewState) {
        binding.viewState = viewState
        binding.executePendingBindings()
    }

    override fun handleViewEffect(event: CounterViewEffect) {
        when (event) {
            is CounterViewEffect.NavigateToSecondScreen -> navigateToSecondScreen()
        }
    }

    override fun emitIntents(): Observable<CounterIntent> = Observable.merge(
        listOf(
            binding.increaseButton clicksTo CounterIntent.Increase,
            binding.decreaseButton clicksTo CounterIntent.Decrease,
            binding.navigateForwardButton clicksTo CounterIntent.NavigateToSecondScreen
        )
    )

    private fun navigateToSecondScreen() {
        startActivity(
            Intent(
                this,
                DogsActivity::class.java
            )
        )
    }
}
```

### Bonus - testing! 
We create a viewRobot class which will pretend to be a view (fragment/activity). Thanks to this we will be able to effectively test each layer of the application using only unit tests. No espresso needed!
```kotlin
class CounterViewRobot(
    presenter: CounterPresenter
) : ViewRobot<CounterViewState, CounterViewEffect, CounterView, CounterPresenter>(presenter) {

    private val increaseSubject = PublishSubject.create<CounterIntent.Increase>()
    private val decreaseSubject = PublishSubject.create<CounterIntent.Decrease>()
    private val navigateToSecondScreenSubject =
        PublishSubject.create<CounterIntent.NavigateToSecondScreen>()

    override val view: CounterView = object : CounterView {
        override fun render(viewState: CounterViewState) {
            renderedStates.add(viewState)
        }

        override fun handleViewEffect(event: CounterViewEffect) {
            emittedViewEffects.add(event)
        }

        override fun emitIntents(): Observable<CounterIntent> = Observable.merge(
            increaseSubject,
            decreaseSubject,
            navigateToSecondScreenSubject
        )
    }

    fun increase() {
        increaseSubject.onNext(CounterIntent.Increase)
    }

    fun decrease() {
        decreaseSubject.onNext(CounterIntent.Decrease)
    }

    fun navigateToSecondScreen() {
        navigateToSecondScreenSubject.onNext(CounterIntent.NavigateToSecondScreen)
    }
}
```
We test the presenter by executing the action that the user would perform and by checking whether the appropriate view state was emitted after the execution of this action:
```kotlin
class CounterPresenterTest {
    private val testScheduler = Schedulers.trampoline()
    private val presenter = CounterPresenter(testScheduler)
    private val viewRobot = CounterViewRobot(presenter)

    @Test
    fun `when increase button clicked then proper view states emitted`() {
        viewRobot.test {
            viewRobot.increase()
        }
        viewRobot.assertViewStates(
            CounterViewState(counterValue = 0),
            CounterViewState(counterValue = 1)
        )
    }

    @Test
    fun `when increase button clicked then no view effects emitted`() {
        viewRobot.test {
            viewRobot.increase()
        }
        viewRobot.assertViewEffects()
    }

    @Test
    fun `when decrease button clicked then proper view states emitted`() {
        viewRobot.test {
            viewRobot.decrease()
        }
        viewRobot.assertViewStates(
            CounterViewState(counterValue = 0),
            CounterViewState(counterValue = -1)
        )
    }

    @Test
    fun `when decrease button clicked then no view effects emitted`() {
        viewRobot.test {
            viewRobot.decrease()
        }
        viewRobot.assertViewEffects()
    }

    @Test
    fun `when navigate to second screen button clicked then proper view effects emitted`() {
        viewRobot.test {
            viewRobot.navigateToSecondScreen()
        }
        viewRobot.assertViewEffects(
            CounterViewEffect.NavigateToSecondScreen
        )
    }

    @Test
    fun `when navigate to second screen button clicked then only default view state emitted`() {
        viewRobot.test {
            viewRobot.navigateToSecondScreen()
        }
        viewRobot.assertViewStates(CounterViewState())
    }
}
```

### More complex examples can be found in the "sample" app.

### License
Free software, yeah!
MIT: <https://rem.mit-license.org>
