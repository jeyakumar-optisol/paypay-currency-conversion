package io.digikraft.photosapp.ui.menu_home

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.digikraft.photosapp.R
import io.digikraft.photosapp.databinding.FragmentMenuHomeBinding
import io.digikraft.photosapp.ui.menu_home.adapter.HomeStaffAdapter
import io.digikraft.photosapp.ui.vendor.VendorFragment
import io.digikraft.domain.model.event.item.EventItem
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.fragment.goto
import io.digikraft.utility.fragment.launchAndRepeatWithViewLifecycle
import io.digikraft.utility.view.EndlessRecyclerViewScrollListener
import kotlinx.coroutines.flow.first

@AndroidEntryPoint
class MenuHomeFragment : BaseFragment<FragmentMenuHomeBinding, HomeViewModel>(
    FragmentMenuHomeBinding::inflate,
    HomeViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)
        initData()
        initUi()
        initObserver()
        initListener()
    }

    private fun initData() {
        //todo: handle intent data
    }

    private fun initObserver() {
        launchAndRepeatWithViewLifecycle {
            viewModel.eventList.collect {
                (binding.staffPickedRecyclerView.adapter as HomeStaffAdapter).submitList(it)
                (binding.happeningRecyclerView.adapter as HomeStaffAdapter).submitList(it)
            }
        }
        launchAndRepeatWithViewLifecycle {
            if (viewModel.eventList.first().isEmpty()) {
                viewModel.fetchEventsList()
            }
        }
    }

    private fun initListener() {
        //todo: handle view listener
        /*FirebaseAuth.getInstance().currentUser?.getIdToken(true)?.addOnCompleteListener { task ->
            Log.e("Jeyk", "Initlistener token: ${task.result.token}")
            runOnAsyncThread {
                viewModel.getEventList(task.result.token!!)
            }
        }
        toast("${FirebaseAuth.getInstance().currentUser?.displayName} <-Name")*/
    }

    private fun initUi() {
        binding.staffPickedRecyclerView.apply {
            addOnScrollListener(object :
                EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    viewModel.fetchEventsList()
                }
            })
            adapter = HomeStaffAdapter(itemListener)
        }
        binding.happeningRecyclerView.apply {
            adapter = HomeStaffAdapter(itemListener)
        }
    }

    private val itemListener = object : HomeStaffAdapter.ItemListener {
        override fun onItemSelected(position: Int, item: EventItem) {
            goto(R.id.vendorFragment, bundleOf(VendorFragment.ARG_EVENT_ITEM to item))
        }
    }
}