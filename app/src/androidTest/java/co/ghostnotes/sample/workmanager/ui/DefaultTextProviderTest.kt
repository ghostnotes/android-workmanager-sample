package co.ghostnotes.sample.workmanager.ui

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import co.ghostnotes.sample.workmanager.R
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DefaultTextProviderTest {

    private lateinit var appContext: Context
    private lateinit var textProvider: DefaultTextProvider

    @Before
    fun setUp() {
        appContext = InstrumentationRegistry.getTargetContext()
        textProvider = DefaultTextProvider(appContext)
    }

    @Test
    fun getProcessing() {
        val expected = appContext.getString(R.string.message_processing)

        // Call
        val actual = textProvider.getProcessing()

        // Test
        assertThat(actual, `is`(expected))
    }

    @Test
    fun getHi() {
        val expected = appContext.getString(R.string.message_hi)

        // Call
        val actual = textProvider.getHi()

        // Test
        assertThat(actual, `is`(expected))
    }

}