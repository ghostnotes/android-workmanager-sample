package co.ghostnotes.sample.workmanager.workers

interface WorkerHelper {

    @Throws(InterruptedException::class)
    fun heavyProcess()

}