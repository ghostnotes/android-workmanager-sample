package co.ghostnotes.sample.workmanager.workers

class DefaultWorkerHelper: WorkerHelper {

    companion object {
        private const val DELAY_TIME_MILLS_LIGHT = 1000L
        private const val DELAY_TIME_MILLS_NORMAL = 2000L
        private const val DELAY_TIME_MILLS_HEAVY = 3000L
    }

    override fun lightProcess() {
        Thread.sleep(DELAY_TIME_MILLS_LIGHT)
    }

    override fun normalProcess() {
        Thread.sleep(DELAY_TIME_MILLS_NORMAL)
    }

    override fun heavyProcess() {
        Thread.sleep(DELAY_TIME_MILLS_HEAVY)
    }

}