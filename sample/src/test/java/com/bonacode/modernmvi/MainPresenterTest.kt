package com.bonacode.modernmvi

import com.bonacode.modernmvi.sample.feature.main.MainPresenter
import com.bonacode.modernmvi.sample.feature.main.MainViewEffect
import com.bonacode.modernmvi.sample.feature.main.MainViewState
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

class MainPresenterTest {
    private val testScheduler = Schedulers.trampoline()
    private val presenter = MainPresenter(testScheduler)
    private val viewRobot = MainPresenterViewRobot(presenter)

    @Test
    fun `when increase button clicked then counter value increased`() {
        viewRobot.test {
            viewRobot.increase()
        }

        viewRobot.assertViewStates(
            MainViewState(counterValue = 0),
            MainViewState(counterValue = 1)
        )
    }

    @Test
    fun `when decrease button clicked then counter value decreased`() {
        viewRobot.test {
            viewRobot.decrease()
        }

        viewRobot.assertViewStates(
            MainViewState(counterValue = 0),
            MainViewState(counterValue = -1)
        )
    }

    @Test
    fun `when navigate to second screen button clicked then navigation event emitted`() {
        viewRobot.test {
            viewRobot.navigateToSecondScreen()
        }

        viewRobot.assertViewEffects(
            MainViewEffect.NavigateToSecondScreen
        )
    }

}