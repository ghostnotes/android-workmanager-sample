package co.ghostnotes.sample.workmanager.binding

import android.databinding.BindingAdapter
import android.view.View

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:enabledUnless")
    fun disabledUnless(view: View, enabled: Boolean) {
        view.isEnabled = !enabled
    }

    @JvmStatic
    @BindingAdapter("app:visibleUnless")
    fun goneUnless(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

}