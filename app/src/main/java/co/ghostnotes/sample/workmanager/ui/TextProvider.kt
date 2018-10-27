package co.ghostnotes.sample.workmanager.ui

import android.content.Context
import co.ghostnotes.sample.workmanager.R

class TextProvider(private val context: Context) {

    fun getProcessing(): String = context.getString(R.string.message_processing)

    fun getHi(): String = context.getString(R.string.message_hi)

}