package co.ghostnotes.sample.workmanager.workers

import android.content.Context
import android.support.annotation.VisibleForTesting
import androidx.work.Worker
import androidx.work.WorkerParameters
import timber.log.Timber


class SecondWorker(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {

    private var workerHelper: WorkerHelper = DefaultWorkerHelper()

    override fun doWork(): Result {
        Timber.d("### do work.")

        return try {
            workerHelper.normalProcess()
            Result.SUCCESS
        } catch (e: InterruptedException) {
            Timber.e(e)
            Result.FAILURE
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun setWorkerHelper(helper: WorkerHelper) {
        workerHelper = helper
    }

}