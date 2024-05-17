package com.sopt.now.gs.feature.reserve

import androidx.fragment.app.viewModels
import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.databinding.FragmentReserveBinding

class ReserveFragment : BindingFragment<FragmentReserveBinding>(R.layout.fragment_reserve) {
    private val menuListitems = mutableListOf<GridMenuListItem>()
    private val menuCategoryitems = mutableListOf<GridMenuCategoryItem>()
    private val menuCategory2items = mutableListOf<GridMenuCategory2Item>()
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

        val menuListAdapter = GridMenuListAdapter(requireContext(), menuListitems)
        binding.gvReserveMenuList.adapter = menuListAdapter

        val discountMenuAdapter = DiscountMenuAdapter()
        binding.rcReserveDisountMenu.adapter = discountMenuAdapter
        discountMenuAdapter.setFriendList(viewModel.mockFriendList)

        //임시 데이터
        menuCategoryitems.apply {
            add(GridMenuCategoryItem(R.drawable.ic_launcher_background,"넷플릭스)BIG반반닭강정","9,000원"))
            add(GridMenuCategoryItem(R.drawable.ic_launcher_background,"넷플릭스)BIG반반닭강정","9,000원"))
            add(GridMenuCategoryItem(R.drawable.ic_launcher_background,"넷플릭스)BIG반반닭강정","9,000원"))
            add(GridMenuCategoryItem(R.drawable.ic_launcher_background,"넷플릭스)BIG반반닭강정","9,000원"))
        }
        val menuCategoryAdapter = GridMenuCategoryAdapter(requireContext(), menuCategoryitems)
        binding.gvReserveCategory1Menu.adapter = menuCategoryAdapter

        //임시 데이터
        menuCategory2items.apply {
            add(GridMenuCategory2Item(R.drawable.ic_launcher_background,"핫매콤야끼우동","9,000원","5.0","(후기4)"))
            add(GridMenuCategory2Item(R.drawable.ic_launcher_background,"핫매콤야끼우동","9,000원","5.0","(후기4)"))
            add(GridMenuCategory2Item(R.drawable.ic_launcher_background,"핫매콤야끼우동","9,000원","5.0","(후기4)"))
            add(GridMenuCategory2Item(R.drawable.ic_launcher_background,"핫매콤야끼우동","9,000원","5.0","(후기4)"))
        }
        val menuCategory2Adapter = GridMenuCategory2Adapter(requireContext(), menuCategory2items)
        binding.gvReserveCategory2Menu.adapter = menuCategory2Adapter
    }

}
