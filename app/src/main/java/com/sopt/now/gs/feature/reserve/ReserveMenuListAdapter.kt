package com.sopt.now.gs.feature.reserve

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.sopt.now.gs.R

class GridMenuListAdapter(val context: Context, private val items: MutableList<ReserveMenuListItem>) :
    BaseAdapter() {
    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item_reserve_menu_list,null)

        val currentItem = items[position]

        view.findViewById<ImageView>(R.id.iv_reserve_list_image).setImageResource(currentItem.menuListImage)
        view.findViewById<TextView>(R.id.iv_reserve_list_title).text = currentItem.menuListTitle

        return view
    }
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ReserveMenuListItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

//    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
//        var convertView = view
//        val binding: ItemReserveMenuCategoryBinding
//
//        if (convertView == null) {
//            val inflater = LayoutInflater.from(parent?.context)
//            binding = ItemReserveMenuCategoryBinding.inflate(inflater, parent, false)
//            convertView = binding.root
//            convertView.tag = binding
//        } else {
//            binding = convertView.tag as ItemReserveMenuCategoryBinding
//        }
//
//        val item: GridMenuCategoryItem = items[position]
//        binding.ivReserveMenuCategoryImage.setImageResource(item.menuCategoryImage)
//        binding.ivReserveMenuCategoryTitle.text = item.menuCategoryTitle
//
//        return convertView
//    }
}