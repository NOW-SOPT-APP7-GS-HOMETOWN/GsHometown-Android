package com.sopt.now.gs.feature.reserve

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopt.now.gs.databinding.ItemReserveTopBannerBinding

class ReservePageViewHolder(val binding: ItemReserveTopBannerBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: ReserveBannerEntity) {
        binding.ivReserveBanner.load(data.image)
    }
}