package com.sopt.now.gs.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.now.gs.databinding.ItemMainTopBannerBinding

class HomeBannerAdapter:
    ListAdapter<HomeBanner, HomeBannerViewHolder>(diffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        return HomeBannerViewHolder(
            ItemMainTopBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HomeBanner>() {
            override fun areItemsTheSame(oldItem: HomeBanner, newItem: HomeBanner): Boolean {
                return oldItem.image == newItem.image
            }

            override fun areContentsTheSame(oldItem: HomeBanner, newItem: HomeBanner): Boolean {
                return oldItem == newItem
            }
        }
    }
}