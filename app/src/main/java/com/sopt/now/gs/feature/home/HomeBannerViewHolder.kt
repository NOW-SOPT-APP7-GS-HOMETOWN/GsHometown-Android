package com.sopt.now.gs.feature.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.databinding.ItemMainBannerBinding

class HomeBannerViewHolder(
    private val binding: ItemMainBannerBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: HomeBanner) {
        binding.ivMainTopBannerImage.setImageResource(data.image)
    }
}