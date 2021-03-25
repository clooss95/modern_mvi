package com.bonacode.modernmvi.presentation.feature.counter

import com.bonacode.modernmvi.sample.presentation.feature.counter.CounterPresenter
import com.bonacode.modernmvi.sample.presentation.feature.counter.CounterViewEffect
import com.bonacode.modernmvi.sample.presentation.feature.counter.CounterViewState
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

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