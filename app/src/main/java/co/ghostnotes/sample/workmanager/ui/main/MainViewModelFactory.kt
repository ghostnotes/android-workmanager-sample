package co.ghostnotes.sample.workmanager.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import co.ghostnotes.sample.workmanager.ui.TextProvider

internal class MainViewModelFactory(private val textProvider: TextProvider): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainViewModel(textProvider) as T
    }

}