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

class FirstWorkerTest {

    private lateinit var worker: FirstWorker

    @Mock
    private lateinit var mockContext: Context
    @Mock
    private lateinit var mockWorkerParams: WorkerParameters
    @Mock
    private lateinit var mockWorkerHelper: WorkerHelper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        worker = FirstWorker(mockContext, mockWorkerParams)
        worker.setWorkerHelper(mockWorkerHelper)
    }

    @Test
    fun doWork_success() {
        // Call
        val result = worker.doWork()

        // Test
        verify(mockWorkerHelper, times(1)).lightProcess()
        verify(mockWorkerHelper, never()).normalProcess()
        verify(mockWorkerHelper, never()).heavyProcess()
        assertThat(result, `is`(ListenableWorker.Result.SUCCESS))
    }

    @Test
    fun doWork_failure() {
        // Mock
        `when`(mockWorkerHelper.lightProcess()).thenThrow(InterruptedException())

        // Call
        val result = worker.doWork()

        // Test
        verify(mockWorkerHelper, times(1)).lightProcess()
        verify(mockWorkerHelper, never()).normalProcess()
        verify(mockWorkerHelper, never()).heavyProcess()
        assertThat(result, `is`(ListenableWorker.Result.FAILURE))
    }

}