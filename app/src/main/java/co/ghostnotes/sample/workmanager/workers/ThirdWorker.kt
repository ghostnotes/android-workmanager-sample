package co.ghostnotes.sample.workmanager.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import timber.log.Timber


class ThirdWorker(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {
    override fun doWork(): Result {
        Timber.d("### do work.")

        return try {
            Thread.sleep(3000L)
            Result.SUCCESS
        } catch (e: InterruptedException) {
            Timber.e(e)
            Result.FAILURE
        }
    }

}