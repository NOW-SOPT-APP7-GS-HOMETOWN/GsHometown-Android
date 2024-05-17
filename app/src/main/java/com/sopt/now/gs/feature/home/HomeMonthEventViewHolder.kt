package com.sopt.now.gs.feature.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.databinding.ItemMainMonthEventBinding

class HomeMonthEventViewHolder(
    private val binding: ItemMainMonthEventBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: HomeBanner) {
        binding.ivMainMonthEventImg.setImageResource(data.image)
    }
}