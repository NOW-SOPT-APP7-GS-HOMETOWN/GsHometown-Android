package com.sopt.now.gs.feature.reserve

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.R
import com.sopt.now.gs.databinding.ItemReserveTopBannerBinding

class ViewPagerAdapter(private val listData: ArrayList<ReserveBannerPage>) : RecyclerView.Adapter<ReservePageViewHolder>() {
    lateinit var binding: ItemReserveTopBannerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservePageViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_reserve_top_banner, parent, false)
        return ReservePageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservePageViewHolder, position: Int) {
        val viewHolder: ReservePageViewHolder = holder
        viewHolder.onBind(listData[position % listData.size])
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

}