package com.sopt.now.gs.feature.reserve

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.databinding.ItemReserveDiscountMenuBinding

class DiscountMenuAdapter() : RecyclerView.Adapter<DiscountMenuViewHolder>() {
    private var discountMenuList: List<DiscountMenu> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountMenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReserveDiscountMenuBinding.inflate(inflater, parent, false)
        return DiscountMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscountMenuViewHolder, position: Int) {
        holder.onBind(discountMenuList[position])
    }

    override fun getItemCount() = discountMenuList.size

    fun setFriendList(friendList: List<DiscountMenu>) {
        this.discountMenuList = friendList.toList()
        notifyDataSetChanged()
    }
}