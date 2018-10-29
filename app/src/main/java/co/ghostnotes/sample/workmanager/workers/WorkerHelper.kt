package co.ghostnotes.sample.workmanager.workers

interface WorkerHelper {

    @Throws(InterruptedException::class)
    fun lightProcess()

    @Throws(InterruptedException::class)
    fun normalProcess()

    @Throws(InterruptedException::class)
    fun heavyProcess()

}