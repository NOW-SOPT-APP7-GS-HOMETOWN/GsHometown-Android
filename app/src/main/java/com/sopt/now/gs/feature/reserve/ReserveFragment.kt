package com.sopt.now.gs.feature.reserve

import androidx.fragment.app.viewModels
import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.databinding.FragmentReserveBinding

class ReserveFragment : BindingFragment<FragmentReserveBinding>(R.layout.fragment_reserve) {
    private val menuListitems = mutableListOf<GridMenuListItem>()
    private val viewModel by viewModels<DiscountMenuViewModel>()

    override fun initView() {
        menuListitems.apply {
            add(GridMenuListItem(R.drawable.img_reserve_pystor, "편스토랑"))
            add(GridMenuListItem(R.drawable.img_reserve_meal, "밀박스25"))
            add(GridMenuListItem(R.drawable.img_reserve_dosyrak, "도시락"))
            add(GridMenuListItem(R.drawable.img_reserve_bread, "빵"))
            add(GridMenuListItem(R.drawable.img_reserve_ganpn, "냉장간편식"))
            add(GridMenuListItem(R.drawable.img_reserve_salad, "샐러드"))
            add(GridMenuListItem(R.drawable.img_reserve_drink, "주류예약"))
            add(GridMenuListItem(R.drawable.img_reserve_salad, "신선식품"))
            add(GridMenuListItem(R.drawable.img_reserve_gimbab, "김밥/주먹밥"))
            add(GridMenuListItem(R.drawable.img_reserve_pasta, "요리반찬/조리면"))
            add(GridMenuListItem(R.drawable.img_reserve_hamburger, "샌드위치/햄버거"))
        }

        val menuCategoryAdapter = GridMenuCategoryAdapter(requireContext(), menuListitems)
        binding.gvReserveMenuList.adapter = menuCategoryAdapter


        val discountMenuAdapter = DiscountMenuAdapter()
        binding.rcReserveDisountMenu.adapter = discountMenuAdapter
        discountMenuAdapter.setFriendList(viewModel.mockFriendList)
    }

}
