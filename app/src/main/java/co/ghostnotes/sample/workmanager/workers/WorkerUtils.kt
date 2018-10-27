package co.ghostnotes.sample.workmanager.workers

import timber.log.Timber

class WorkerUtils {

    companion object {
        private const val DELAY_TIME_MILLS = 3000L
    }

    fun sleep() {
        try {
            Thread.sleep(DELAY_TIME_MILLS)
        } catch (e: InterruptedException) {
            Timber.e(e)
            Thread.interrupted()
        }
    }

}