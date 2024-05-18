package com.sopt.now.gs.feature.reserve

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.databinding.ItemReserveDiscountMenuBinding

class ReserveDiscountAdapter() : RecyclerView.Adapter<ReserveDiscountViewHolder>() {
    private var discountMenuList: List<ReserveDiscountMenu> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReserveDiscountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReserveDiscountMenuBinding.inflate(inflater, parent, false)
        return ReserveDiscountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReserveDiscountViewHolder, position: Int) {
        holder.onBind(discountMenuList[position])
    }

    override fun getItemCount() = discountMenuList.size

    fun setFriendList(friendList: List<ReserveDiscountMenu>) {
        this.discountMenuList = friendList.toList()
        notifyDataSetChanged()
    }
}