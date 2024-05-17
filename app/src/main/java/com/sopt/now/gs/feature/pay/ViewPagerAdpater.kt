package com.sopt.now.gs.feature.pay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sopt.now.gs.core.view.ItemDiffCallback
import com.sopt.now.gs.databinding.ItemViewPageAutoBinding

class ViewPagerAdpater : ListAdapter<DataPage, PageViewHolder>(reqresDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewPageAutoBinding.inflate(inflater, parent, false)
        return PageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.onBind(currentList[position % currentList.size])
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    companion object {
        private val reqresDiffCallback =
            ItemDiffCallback<DataPage>(
                onItemsTheSame = { old, new -> old.title == new.title },
                onContentsTheSame = { old, new -> old == new },
            )
    }
}
