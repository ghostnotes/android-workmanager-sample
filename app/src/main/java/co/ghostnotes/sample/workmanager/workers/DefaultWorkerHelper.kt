package co.ghostnotes.sample.workmanager.workers

class DefaultWorkerHelper: WorkerHelper {

    companion object {
        private const val DELAY_TIME_MILLS = 3000L
    }

    override fun heavyProcess() {
        Thread.sleep(DELAY_TIME_MILLS)
    }

}