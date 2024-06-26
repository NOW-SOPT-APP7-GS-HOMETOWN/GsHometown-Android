package com.sopt.now.gs.feature.reserve

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.data.response.ResponseReserveGspayDto
import com.sopt.now.gs.databinding.ItemReserveDiscountMenuBinding
import com.sopt.now.gs.feature.reserve.adapter.ReserveDiscountViewHolder

class ReserveDiscountAdapter() : RecyclerView.Adapter<ReserveDiscountViewHolder>() {
    private var discountMenuList: List<ResponseReserveGspayDto.Product> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReserveDiscountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReserveDiscountMenuBinding.inflate(inflater, parent, false)
        return ReserveDiscountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReserveDiscountViewHolder, position: Int) {
        holder.onBind(discountMenuList[position])
    }

    override fun getItemCount() = discountMenuList.size

    fun setDiscountMenuList(discountMenuList: List<ResponseReserveGspayDto.Product>) {
        this.discountMenuList = discountMenuList.toList()
        notifyDataSetChanged()
    }
}