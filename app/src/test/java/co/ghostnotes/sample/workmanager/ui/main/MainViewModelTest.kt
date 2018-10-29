package co.ghostnotes.sample.workmanager.ui.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import androidx.work.*
import co.ghostnotes.sample.workmanager.ui.TextProvider
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class MainViewModelTest {

    //inline fun <reified T> lambdaMock(): T = mock(T::class.java)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var mockTextProvider: TextProvider
    @Mock
    private lateinit var mockWorkManager: WorkManager
    @Mock
    private lateinit var mockWorkStatuses: LiveData<List<WorkStatus>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        // Mock
        `when`(mockWorkManager.getStatusesByTagLiveData(MainViewModel.WORK_TAG_NAME_TEST)).thenReturn(mockWorkStatuses)

        viewModel = MainViewModel(mockTextProvider, mockWorkManager)
    }

    @Test
    fun isProcessing() {
        assertThat(viewModel.isProcessing.value, `is`(false))

        // Test
        viewModel.setProcessing(true)
        assertThat(viewModel.isProcessing.value, `is`(true))

        viewModel.setProcessing(false)
        assertThat(viewModel.isProcessing.value, `is`(false))
    }

    @Test
    fun workStatuses() {
        // Test
        val workStatuses = viewModel.workStatuses
        assertThat(workStatuses, `is`(mockWorkStatuses))
    }

    @Test
    fun messageText() {
        // Mock
        val processingText = "processing"
        val hi = "Hi"
        `when`(mockTextProvider.getProcessing()).thenReturn(processingText)
        `when`(mockTextProvider.getHi()).thenReturn(hi)

        val mockObserver = mock<Observer<String>>()
        viewModel.messageText.observeForever(mockObserver)

        // Test
        viewModel.setProcessing(true)
        verify(mockObserver, times(1)).onChanged(processingText)

        viewModel.setProcessing(false)
        verify(mockObserver, times(2)).onChanged(hi)
    }

    @Test
    fun startWorkers() {
        // Mock
        val mockWorkContinuation = mock<WorkContinuation>()
        `when`(mockWorkManager.beginUniqueWork(any<String>(), any<ExistingWorkPolicy>(), any<List<OneTimeWorkRequest>>())).thenReturn(mockWorkContinuation)
        `when`(mockWorkContinuation.then(any<List<OneTimeWorkRequest>>())).thenReturn(mockWorkContinuation)
        `when`(mockWorkContinuation.then(any<OneTimeWorkRequest>())).thenReturn(mockWorkContinuation)

        // Call
        viewModel.startWorkers()

        // Test
        verify(mockWorkManager, times(1)).cancelAllWork()

        verify(mockWorkManager, times(1)).beginUniqueWork(any<String>(), any<ExistingWorkPolicy>(), any<List<OneTimeWorkRequest>>())
        verify(mockWorkContinuation, times(1)).then(any<List<OneTimeWorkRequest>>())
        verify(mockWorkContinuation, times(1)).then(any<OneTimeWorkRequest>())
        verify(mockWorkContinuation, times(1)).enqueue()
    }

    @Test
    fun cancelWorkers() {
        // Call
        viewModel.cancelWorkers()

        // Test
        verify(mockWorkManager, times(1)).cancelUniqueWork(MainViewModel.WORK_NAME_TEST)
    }

}