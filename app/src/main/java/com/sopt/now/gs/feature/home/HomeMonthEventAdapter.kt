package com.sopt.now.gs.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.now.gs.databinding.ItemHomeMonthEventBinding

class HomeMonthEventAdapter : ListAdapter<String, HomeMonthEventViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMonthEventViewHolder {
        return HomeMonthEventViewHolder(
            ItemHomeMonthEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeMonthEventViewHolder, position: Int) {
        holder.onBind(currentList[position % currentList.size])
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}
