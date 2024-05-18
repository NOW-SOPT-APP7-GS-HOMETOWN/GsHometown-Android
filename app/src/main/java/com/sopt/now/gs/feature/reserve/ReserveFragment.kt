package com.sopt.now.gs.feature.reserve

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.databinding.FragmentReserveBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlin.math.ceil

class ReserveFragment : BindingFragment<FragmentReserveBinding>(R.layout.fragment_reserve) {
    private val menuListitems = mutableListOf<GridMenuListItem>()
    private val menuCategoryitems = mutableListOf<GridMenuCategoryItem>()
    private val menuCategory2items = mutableListOf<GridMenuCategory2Item>()
    private val viewModel by viewModels<DiscountMenuViewModel>()
    private var bannerPosition = 0
    lateinit var job: Job

    override fun initView() {
        //임시 데이터 생성
        val list: ArrayList<DataPage> = ArrayList<DataPage>().let {
            it.apply {
                add(DataPage(R.drawable.img_reserve_banner1, "1"))
                add(DataPage(R.drawable.img_reserve_banner2, "2"))
                add(DataPage(R.drawable.img_reserve_banner1, "3"))
                add(DataPage(R.drawable.img_reserve_banner2, "4"))
                add(DataPage(R.drawable.img_reserve_banner1, "5"))
                add(DataPage(R.drawable.img_reserve_banner2, "6"))
            }
        }

        binding.vpReserveBanner.adapter = ViewPagerAdapter(list)
        binding.vpReserveBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.tvReservePages.text = getString(R.string.reserve_top_banner, 1, list.size)

        binding.vpReserveBanner.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            //사용자가 스크롤 했을때 position 수정
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bannerPosition = position
                binding.tvReservePages.text = getString(
                    R.string.reserve_top_banner,
                    (bannerPosition % list.size) + 1,
                    list.size
                )
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (state) {
                    ViewPager2.SCROLL_STATE_IDLE ->{
                        if (!job.isActive) scrollJobCreate()
                    }

                    ViewPager2.SCROLL_STATE_DRAGGING -> job.cancel()

                    ViewPager2.SCROLL_STATE_SETTLING -> {}
                }
            }
        })
        bannerPosition = Int.MAX_VALUE / 2 - ceil(list.size.toDouble() / 2).toInt()
        binding.vpReserveBanner.setCurrentItem(bannerPosition, false)

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
            add(GridMenuCategoryItem(R.drawable.img_reserve_category1_chicken, "넷플릭스)BIG반반닭강정", "9,000원"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_category1_chicken_half, "넷플릭스)BIG반반닭강정", "9,000원"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_category1_cutlet, "넷플릭스)BIG반반닭강정", "9,000원"))
            add(GridMenuCategoryItem(R.drawable.img_reserve_category1_pork_skin, "넷플릭스)BIG반반닭강정", "9,000원"))
        }
        val menuCategoryAdapter = GridMenuCategoryAdapter(requireContext(), menuCategoryitems)
        binding.gvReserveCategory1Menu.adapter = menuCategoryAdapter

        //임시 데이터
        menuCategory2items.apply {
            add(
                GridMenuCategory2Item(
                    R.drawable.img_reserve_category2_chicken_soup,
                    "핫매콤야끼우동",
                    "9,000원",
                    "5.0",
                    "(후기4)"
                )
            )
            add(
                GridMenuCategory2Item(
                    R.drawable.img_reserve_category2_meat_soup,
                    "핫매콤야끼우동",
                    "9,000원",
                    "5.0",
                    "(후기4)"
                )
            )
            add(
                GridMenuCategory2Item(
                    R.drawable.img_reserve_category2_noodle,
                    "핫매콤야끼우동",
                    "9,000원",
                    "5.0",
                    "(후기4)"
                )
            )
            add(
                GridMenuCategory2Item(
                    R.drawable.img_reserve_category2_potato_soup,
                    "핫매콤야끼우동",
                    "9,000원",
                    "5.0",
                    "(후기4)"
                )
            )
        }
        val menuCategory2Adapter = GridMenuCategory2Adapter(requireContext(), menuCategory2items)
        binding.gvReserveCategory2Menu.adapter = menuCategory2Adapter

    }

    override fun onResume() {
        super.onResume()
        scrollJobCreate()
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }

    fun scrollJobCreate() {
        job = lifecycleScope.launchWhenResumed {
            delay(1500)
            binding.vpReserveBanner.setCurrentItem(++bannerPosition, true)
        }
    }
}
