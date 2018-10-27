package co.ghostnotes.sample.workmanager

import android.app.Application
import timber.log.Timber

class WorkManagerSampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}