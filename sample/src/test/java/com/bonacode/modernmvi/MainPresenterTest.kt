package com.bonacode.modernmvi

import com.bonacode.modernmvi.sample.presentation.feature.counter.CounterPresenter
import com.bonacode.modernmvi.sample.presentation.feature.counter.CounterViewEffect
import com.bonacode.modernmvi.sample.presentation.feature.counter.CounterViewState
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

class MainPresenterTest {
    private val testScheduler = Schedulers.trampoline()
    private val presenter = CounterPresenter(testScheduler)
    private val viewRobot = MainPresenterViewRobot(presenter)

    @Test
    fun `when increase button clicked then counter value increased`() {
        viewRobot.test {
            viewRobot.increase()
        }

        viewRobot.assertViewStates(
            CounterViewState(counterValue = 0),
            CounterViewState(counterValue = 1)
        )
    }

    @Test
    fun `when decrease button clicked then counter value decreased`() {
        viewRobot.test {
            viewRobot.decrease()
        }

        viewRobot.assertViewStates(
            CounterViewState(counterValue = 0),
            CounterViewState(counterValue = -1)
        )
    }

    @Test
    fun `when navigate to second screen button clicked then navigation event emitted`() {
        viewRobot.test {
            viewRobot.navigateToSecondScreen()
        }

        viewRobot.assertViewEffects(
            CounterViewEffect.NavigateToSecondScreen
        )
    }

}