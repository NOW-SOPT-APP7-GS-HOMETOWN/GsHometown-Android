package com.sopt.now.gs.feature.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.databinding.ItemHomeBannerBinding

class HomeBannerViewHolder(
    private val binding: ItemHomeBannerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: HomeBanner) {
        binding.ivMainTopBannerImage.setImageResource(data.image)
    }
}