package com.sopt.now.gs.feature.reserve.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopt.now.gs.databinding.ItemReserveTopBannerBinding
import com.sopt.now.gs.feature.reserve.ReserveBannerEntity

class ReservePageViewHolder(val binding: ItemReserveTopBannerBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: ReserveBannerEntity) {
        binding.ivReserveBanner.load(data.image)
    }
}