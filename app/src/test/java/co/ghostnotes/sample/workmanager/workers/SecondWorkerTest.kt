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

class SecondWorkerTest {

    private lateinit var worker: SecondWorker

    @Mock
    private lateinit var mockContext: Context
    @Mock
    private lateinit var mockWorkerParams: WorkerParameters
    @Mock
    private lateinit var mockWorkerHelper: WorkerHelper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        worker = SecondWorker(mockContext, mockWorkerParams)
        worker.setWorkerHelper(mockWorkerHelper)
    }

    @Test
    fun doWork_success() {
        // Call
        val result = worker.doWork()

        // Test
        verify(mockWorkerHelper, times(1)).normalProcess()
        verify(mockWorkerHelper, never()).lightProcess()
        verify(mockWorkerHelper, never()).heavyProcess()
        assertThat(result, `is`(ListenableWorker.Result.SUCCESS))
    }

    @Test
    fun doWork_failure() {
        // Mock
        `when`(mockWorkerHelper.normalProcess()).thenThrow(InterruptedException())

        // Call
        val result = worker.doWork()

        // Test
        verify(mockWorkerHelper, times(1)).normalProcess()
        verify(mockWorkerHelper, never()).lightProcess()
        verify(mockWorkerHelper, never()).heavyProcess()
        assertThat(result, `is`(ListenableWorker.Result.FAILURE))
    }

}