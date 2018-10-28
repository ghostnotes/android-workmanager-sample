package co.ghostnotes.sample.workmanager.binding

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.View
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BindingAdaptersTest {

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getTargetContext()
    }

    @Test
    fun disableUnless() {
        val view = View(context)
        // Check the default value.
        assertThat(view.isEnabled, `is`(true))

        // Test
        BindingAdapters.disabledUnless(view, true)
        assertThat(view.isEnabled, `is`(false))

        BindingAdapters.disabledUnless(view, false)
        assertThat(view.isEnabled, `is`(true))
    }

    @Test
    fun goneUnless() {
        val view = View(context)
        // Check the default value.
        assertThat(view.visibility, `is`(View.VISIBLE))

        // Test
        // View.GONE
        BindingAdapters.goneUnless(view, false)
        assertThat(view.visibility, `is`(View.GONE))

        // View.VISIBLE
        BindingAdapters.goneUnless(view, true)
        assertThat(view.visibility, `is`(View.VISIBLE))
    }

}