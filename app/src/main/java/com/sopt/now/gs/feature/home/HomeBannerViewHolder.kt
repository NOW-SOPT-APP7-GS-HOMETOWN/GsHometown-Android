package com.sopt.now.gs.feature.home

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopt.now.gs.databinding.ItemHomeBannerBinding

class HomeBannerViewHolder(
    private val binding: ItemHomeBannerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: String) {
        binding.ivMainTopBannerImage.load(data)
    }
}