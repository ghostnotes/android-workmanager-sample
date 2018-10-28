package co.ghostnotes.sample.workmanager.ui

import android.content.Context
import co.ghostnotes.sample.workmanager.R

class DefaultTextProvider(private val context: Context): TextProvider {

    override fun getProcessing(): String = context.getString(R.string.message_processing)

    override fun getHi(): String = context.getString(R.string.message_hi)

}