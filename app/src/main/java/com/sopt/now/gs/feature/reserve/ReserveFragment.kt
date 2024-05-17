package com.sopt.now.gs.feature.reserve

import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.databinding.FragmentReserveBinding

class ReserveFragment : BindingFragment<FragmentReserveBinding>(R.layout.fragment_reserve) {
    private val menuCategoryitems = mutableListOf<GridMenuCategoryItem>()
    override fun initView() {
        menuCategoryitems.apply {
            add(GridMenuCategoryItem(R.drawable.img_reserve_pystor,"편스토랑"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_meal,"밀박스25"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_dosyrak,"도시락"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_bread,"빵"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_ganpn,"냉장간편식"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_salad,"샐러드"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_drink,"주류예약"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_salad,"신선식품"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_gimbab,"김밥/주먹밥"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_pasta,"요리반찬/조리면"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_hamburger,"샌드위치/햄버거"))
        }

        val menuCategoryAdapter = GridMenuCategoryAdapter(requireContext(),menuCategoryitems)
        binding.gvReserveMenuCategory.adapter = menuCategoryAdapter
    }
}
