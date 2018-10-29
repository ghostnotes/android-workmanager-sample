package co.ghostnotes.sample.workmanager.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import androidx.work.*
import co.ghostnotes.sample.workmanager.ui.TextProvider
import co.ghostnotes.sample.workmanager.workers.FirstWorker
import co.ghostnotes.sample.workmanager.workers.SecondWorker
import co.ghostnotes.sample.workmanager.workers.ThirdWorker
import timber.log.Timber

class MainViewModel(textProvider: TextProvider, private val workManager: WorkManager = WorkManager.getInstance()) : ViewModel() {

    companion object {
        private const val BASE_WORK_NAME = "co.ghostnotes.sample.workmanager"
        internal const val WORK_NAME_TEST = "$BASE_WORK_NAME.TEST"

        internal const val WORK_TAG_NAME_TEST = "WORK_TAG_NAME_TEST"
    }

    private val _processing: MutableLiveData<Boolean> = MutableLiveData()
    val isProcessing = _processing
    fun setProcessing(value: Boolean) {
        if (_processing.value != value) {
            _processing.value = value
        }
    }

    internal val workStatuses: LiveData<List<WorkStatus>>

    init {
        _processing.value = false
        workStatuses = workManager.getStatusesByTagLiveData(WORK_TAG_NAME_TEST)
    }

    val messageText: LiveData<String> = Transformations.map(_processing) {
        if (it) {
            textProvider.getProcessing()
        } else {
            textProvider.getHi()
        }
    }

    fun startWorkers() {
        Timber.d("### start workers.")
        workManager.cancelAllWork()

        _processing.value = true

        val firstWorker = OneTimeWorkRequest.from(FirstWorker::class.java)
        val secondWorker = OneTimeWorkRequest.from(SecondWorker::class.java)
        //val constraints = Constraints.Builder()
        //    .setRequiresCharging(true)
        //    .build()
        val thirdWorker = OneTimeWorkRequestBuilder<ThirdWorker>()
            //.setConstraints(constraints)
            .addTag(WORK_TAG_NAME_TEST)
            .build()

        workManager.beginUniqueWork(WORK_NAME_TEST, ExistingWorkPolicy.REPLACE, firstWorker)
            .then(secondWorker)
            .then(thirdWorker)
            .enqueue()
    }

    fun cancelWorkers() {
        workManager.cancelUniqueWork(WORK_NAME_TEST)
    }

}