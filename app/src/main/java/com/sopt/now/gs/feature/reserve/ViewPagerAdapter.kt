package com.sopt.now.gs.feature.reserve

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.R
import com.sopt.now.gs.databinding.ItemReserveTopBannerBinding

class ViewPagerAdapter(private val listData: ArrayList<DataPage>) : RecyclerView.Adapter<ViewHolderPage>() {
    lateinit var binding: ItemReserveTopBannerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPage {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_reserve_top_banner, parent, false)
        return ViewHolderPage(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderPage, position: Int) {
        val viewHolder: ViewHolderPage = holder
        viewHolder.onBind(listData[position % listData.size])
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

}

class ViewHolderPage(val binding: ItemReserveTopBannerBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: DataPage) {
        binding.ivReserveBanner.setImageResource(data.image)
    }
}