package com.sopt.now.gs.feature.pay

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.databinding.ItemViewPageAutoBinding

class PageViewHolder(val binding: ItemViewPageAutoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: DataPage) {
        binding.tvTitle.text = data.title
        binding.clItemViewPageAuto.setBackgroundResource(data.color)
    }
}
