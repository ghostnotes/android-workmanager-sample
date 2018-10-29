package co.ghostnotes.sample.workmanager.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ThirdWorkerTest {

    private lateinit var worker: ThirdWorker

    @Mock
    private lateinit var mockContext: Context
    @Mock
    private lateinit var mockWorkerParams: WorkerParameters
    @Mock
    private lateinit var mockWorkerHelper: WorkerHelper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        worker = ThirdWorker(mockContext, mockWorkerParams)
        worker.setWorkerHelper(mockWorkerHelper)
    }

    @Test
    fun doWork_success() {
        // Call
        val result = worker.doWork()

        // Test
        verify(mockWorkerHelper, times(1)).heavyProcess()
        assertThat(result, `is`(ListenableWorker.Result.SUCCESS))
    }

    @Test
    fun doWork_failure() {
        // Mock
        `when`(mockWorkerHelper.heavyProcess()).thenThrow(InterruptedException())

        // Call
        val result = worker.doWork()

        // Test
        verify(mockWorkerHelper, times(1)).heavyProcess()
        assertThat(result, `is`(ListenableWorker.Result.FAILURE))
    }

}