package io.blix.photosapp.ui.menu_tickets.past

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import io.blix.photosapp.R
import io.blix.photosapp.databinding.FragmentPastTicketsBinding
import io.blix.photosapp.ui.menu_tickets.MyTicketViewModel
import io.blix.photosapp.ui.menu_tickets.active.adapter.ActiveTicketAdapter
import io.blix.photosapp.ui.menu_tickets.past.adapter.PastTicketAdapter
import io.digikraft.domain.model.market.MarketItem
import io.digikraft.domain.model.ticket.MyTicketItem
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.fragment.goto


class PastTicketsFragment : BaseFragment<FragmentPastTicketsBinding, MyTicketViewModel>(
    FragmentPastTicketsBinding::inflate, MyTicketViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        val list = ArrayList<MyTicketItem>()
        for (i in 0 until 10) {
            list.add(MyTicketItem())
        }
        binding.recyclerView.apply {
            adapter = PastTicketAdapter(itemListener).apply {
                submitList(list)
            }
        }
    }

    private val itemListener = object : PastTicketAdapter.ItemListener {
        override fun onItemSelected(position: Int, item: MyTicketItem) {
            goto(R.id.vendorFragment)
        }
    }

}