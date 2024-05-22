package com.sopt.now.gs.feature.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.databinding.ItemHomeMonthEventBinding

class HomeMonthEventViewHolder(
    private val binding: ItemHomeMonthEventBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: HomeBanner) {
        binding.ivMainMonthEventImg.setImageResource(data.image)
    }
}