package com.dodemy.cryptocurrency

import com.dodemy.cryptocurrency.mainView.viewModel.MainViewModel
import org.junit.Test

import org.junit.Assert.*
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dodemy.cryptocurrency.mainView.model.CoinModel
import com.dodemy.cryptocurrency.mainView.network.ApiService
import io.reactivex.disposables.Disposable
import io.reactivex.Scheduler
import io.reactivex.observers.TestObserver
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import org.junit.BeforeClass
import org.junit.Rule


class ExampleUnitTest {

    @get:Rule
    //val rule = InstantTaskExecutorRule()

    private val viewModel = MainViewModel(ApiServiceMock())
    private val apiService = ApiService("http://localhost:1234/androidmvvmdemo/")

    private val testObserver = TestObserver<List<CoinModel>>()

    @Test
    fun viewModelTest() {

        val initialCount = viewModel.coinsCount

        viewModel.getCoinsData()

        //assertEquals(viewModel.coinsCount, 2)
        assertTrue(viewModel.coinsCount > initialCount)

        assertFalse(viewModel.progress.value!!)
        assertEquals(viewModel.errors.value?.getMessage(), null)
    }

    @Test
    fun apiTest() {
        apiService.getAllCoins()
            .subscribe {
                assertTrue(it.count() > 0)
            }
    }

    @Test
    fun observableApiTest() {
        apiService.getAllCoins()
            .subscribe(testObserver)

        testObserver.assertSubscribed()
            .assertComplete()
            .assertNoErrors()
    }


    companion object {
        @BeforeClass @JvmStatic fun setUpRxSchedulers() {
            val immediate = object : Scheduler() {
                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                    // this prevents StackOverflowErrors when scheduling with a delay
                    return super.scheduleDirect(run, 0, unit)
                }

                override fun createWorker(): Scheduler.Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }

            RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
        }
    }
}
