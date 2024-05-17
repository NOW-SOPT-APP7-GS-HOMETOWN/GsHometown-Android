package com.sopt.now.gs.feature.reserve

import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.databinding.FragmentReserveBinding

class ReserveFragment : BindingFragment<FragmentReserveBinding>(R.layout.fragment_reserve) {
    private val items = mutableListOf<GridMenuCategoryItem>()
    override fun initView() {
        //scope함수 처리 필요
        items.add(GridMenuCategoryItem(R.drawable.img_reserve_pystor,"편스토랑"))
        items.add(GridMenuCategoryItem(R.drawable.img_reserve_meal,"밀박스25"))
        items.add(GridMenuCategoryItem(R.drawable.img_reserve_dosyrak,"도시락"))
        items.add(GridMenuCategoryItem(R.drawable.img_reserve_bread,"빵"))
        items.add(GridMenuCategoryItem(R.drawable.img_reserve_ganpn,"냉장간편식"))
        items.add(GridMenuCategoryItem(R.drawable.img_reserve_salad,"샐러드"))
        items.add(GridMenuCategoryItem(R.drawable.img_reserve_drink,"주류예약"))
        items.add(GridMenuCategoryItem(R.drawable.img_reserve_salad,"신선식품"))
        items.add(GridMenuCategoryItem(R.drawable.img_reserve_gimbab,"김밥/주먹밥"))
        items.add(GridMenuCategoryItem(R.drawable.img_reserve_pasta,"요리반찬/조리면"))
        items.add(GridMenuCategoryItem(R.drawable.img_reserve_hamburger,"샌드위치/햄버거"))

        val menuCategoryAdapter = GridMenuCategoryAdapter(requireContext(),items)
        binding.gvReserveMenuCategory.adapter = menuCategoryAdapter
    }
}
