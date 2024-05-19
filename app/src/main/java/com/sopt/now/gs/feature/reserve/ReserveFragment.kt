package com.sopt.now.gs.feature.reserve

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.databinding.FragmentReserveBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.ceil

class ReserveFragment : BindingFragment<FragmentReserveBinding>(R.layout.fragment_reserve) {
    lateinit var banner: Job
    private var bannerPosition = 0
    private val bannerItems = ArrayList<ReserveBannerPage>()
    private val menuListItems = mutableListOf<ReserveMenuListItem>()
    private val category1MenuItems = mutableListOf<ReserveMenuCategoryItem>()
    private val category2MenuItems = mutableListOf<ReserveMenuCategory2Item>()
    private val discountMenuViewModel by viewModels<ReserveDiscountViewModel>()

    override fun initView() {
        setBannerItems()
        initBannerAdapter()
        setBannerPositionText()
        updateBannerPosition()

        setMenuListItems()
        initMenuListAdapter()

        initDiscountMenuAdapter()

        setMenuCategory1Items()
        initMenuCategory1Adapter()

        setMenuCategory2Items()
        initMenuCategory2Adapter()

        moveToTop()
    }

    override fun onResume() {
        super.onResume()
        scrollJobCreate()
    }

    override fun onPause() {
        super.onPause()
        banner.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        banner.cancel()
    }

    fun scrollJobCreate() {
        banner = lifecycleScope.launch {
            delay(1500)
            binding.vpReserveBanner.setCurrentItem(++bannerPosition, true)
        }
    }

    private fun setBannerItems(){
        //임시 데이터
        bannerItems.let {
            it.apply {
                add(ReserveBannerPage(R.drawable.img_reserve_banner1, "1"))
                add(ReserveBannerPage(R.drawable.img_reserve_banner2, "2"))
            }
        }
    }

    private fun initBannerAdapter(){
        binding.vpReserveBanner.adapter = ViewPagerAdapter(bannerItems)
        binding.vpReserveBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private fun setBannerPositionText(){
        bannerPosition = Int.MAX_VALUE / 2 - ceil(bannerItems.size.toDouble() / 2).toInt()
        binding.vpReserveBanner.setCurrentItem(bannerPosition, false)
        binding.tvReservePages.text = getString(R.string.reserve_top_banner, 1, bannerItems.size)
    }

    private fun updateBannerPosition(){
        binding.vpReserveBanner.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bannerPosition = position
                binding.tvReservePages.text = getString(
                    R.string.reserve_top_banner,
                    (bannerPosition % bannerItems.size) + 1,
                    bannerItems.size
                )
            }
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (state) {
                    ViewPager2.SCROLL_STATE_IDLE -> {
                        if (!banner.isActive) scrollJobCreate()
                    }

                    ViewPager2.SCROLL_STATE_DRAGGING -> banner.cancel()

                }
            }
        })
    }

    private fun setMenuListItems(){
        menuListItems.apply {
            add(ReserveMenuListItem(R.drawable.img_reserve_pystor, "편스토랑"))
            add(ReserveMenuListItem(R.drawable.img_reserve_meal, "밀박스25"))
            add(ReserveMenuListItem(R.drawable.img_reserve_dosyrak, "도시락"))
            add(ReserveMenuListItem(R.drawable.img_reserve_bread, "빵"))
            add(ReserveMenuListItem(R.drawable.img_reserve_ganpn, "냉장간편식"))
            add(ReserveMenuListItem(R.drawable.img_reserve_salad, "샐러드"))
            add(ReserveMenuListItem(R.drawable.img_reserve_drink, "주류예약"))
            add(ReserveMenuListItem(R.drawable.img_reserve_salad, "신선식품"))
            add(ReserveMenuListItem(R.drawable.img_reserve_gimbab, "김밥/주먹밥"))
            add(ReserveMenuListItem(R.drawable.img_reserve_pasta, "요리반찬/조리면"))
            add(ReserveMenuListItem(R.drawable.img_reserve_hamburger, "샌드위치/햄버거"))
        }
    }

    private fun initMenuListAdapter(){
        val menuListAdapter = GridMenuListAdapter(requireContext(), menuListItems)
        binding.gvReserveMenuList.adapter = menuListAdapter
    }

    private fun initDiscountMenuAdapter() {
        val discountMenuAdapter = ReserveDiscountAdapter()
        binding.rcReserveDisountMenu.adapter = discountMenuAdapter
        discountMenuAdapter.setDiscountMenuList(discountMenuViewModel.mockMenuList)
    }

    private fun setMenuCategory1Items() {
        //임시 데이터
        category1MenuItems.apply {
            add(
                ReserveMenuCategoryItem(
                    R.drawable.img_reserve_category1_chicken,
                    "넷플릭스)BIG반반닭강정",
                    9000
                )
            )
            add(
                ReserveMenuCategoryItem(
                    R.drawable.img_reserve_category1_chicken_half,
                    "넷플릭스)BIG반반닭강정",
                    9000
                )
            )
            add(
                ReserveMenuCategoryItem(
                    R.drawable.img_reserve_category1_cutlet,
                    "넷플릭스)BIG반반닭강정",
                    9000
                )
            )
            add(
                ReserveMenuCategoryItem(
                    R.drawable.img_reserve_category1_pork_skin,
                    "넷플릭스)BIG반반닭강정",
                    9000
                )
            )
        }
    }

    private fun initMenuCategory1Adapter() {
        val menuCategoryAdapter = GridMenuCategoryAdapter(requireContext(), category1MenuItems)
        binding.gvReserveCategory1Menu.adapter = menuCategoryAdapter
    }

    private fun setMenuCategory2Items() {
        //임시 데이터
        category2MenuItems.apply {
            add(
                ReserveMenuCategory2Item(
                    R.drawable.img_reserve_category2_chicken_soup,
                    "핫매콤야끼우동",
                    9000,
                    5.0,
                    4
                )
            )
            add(
                ReserveMenuCategory2Item(
                    R.drawable.img_reserve_category2_meat_soup,
                    "핫매콤야끼우동",
                    9000,
                    5.0,
                    4
                )
            )
            add(
                ReserveMenuCategory2Item(
                    R.drawable.img_reserve_category2_noodle,
                    "핫매콤야끼우동",
                    9000,
                    5.0,
                    4
                )
            )
            add(
                ReserveMenuCategory2Item(
                    R.drawable.img_reserve_category2_potato_soup,
                    "핫매콤야끼우동",
                    9000,
                    5.0,
                    4
                )
            )
        }
    }

    private fun initMenuCategory2Adapter() {
        val menuCategory2Adapter = GridMenuCategory2Adapter(requireContext(), category2MenuItems)
        binding.gvReserveCategory2Menu.adapter = menuCategory2Adapter
    }

    private fun moveToTop() {
        binding.fabReserveButton.setOnClickListener {
            binding.svReserve.smoothScrollTo(0, 0)
        }
    }
}
