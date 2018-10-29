package co.ghostnotes.sample.workmanager.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.work.WorkStatus
import co.ghostnotes.sample.workmanager.R
import co.ghostnotes.sample.workmanager.databinding.FragmentMainBinding
import co.ghostnotes.sample.workmanager.ui.DefaultTextProvider
import timber.log.Timber

class MainFragment : Fragment(), MainContract.View {
    companion object {
        const val FRAGMENT_TAG = "co.ghostnotes.sample.workmanager.ui.main.MainFragment"

        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Timber.d("### on create view.")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.d("### on activity created.")
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, MainViewModelFactory(DefaultTextProvider(context!!))).get(MainViewModel::class.java)
        viewModel.workStatuses.observe(this, WorkStatusObserver(this))

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
    }

    override fun setProcessing(processing: Boolean) {
        viewModel.setProcessing(processing)
    }

    internal class WorkStatusObserver(private val view: MainContract.View): Observer<List<WorkStatus>> {
        override fun onChanged(t: List<WorkStatus>?) {
            if (t == null || t.isEmpty()) return

            val workStatus = t[0]
            if (workStatus.state.isFinished) {
                Timber.d("### on changed. Work is finished.")
                view.setProcessing(false)
            }
        }
    }

}
