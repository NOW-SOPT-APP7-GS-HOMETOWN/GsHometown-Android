package com.sopt.now.gs.feature.reserve

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.core.util.fragment.snackBar
import com.sopt.now.gs.core.view.UiState
import com.sopt.now.gs.data.response.ResponseReserveCategoryDto
import com.sopt.now.gs.data.response.ResponseReserveEventDto
import com.sopt.now.gs.data.response.ResponseReserveGspayDto
import com.sopt.now.gs.databinding.FragmentReserveBinding
import com.sopt.now.gs.feature.reserve.adapter.ReserveBannerAdapter
import com.sopt.now.gs.feature.reserve.adapter.ReserveCategoryBottomAdapter
import com.sopt.now.gs.feature.reserve.adapter.ReserveCategoryTopAdapter
import com.sopt.now.gs.feature.util.KeyStorage.USER_ID
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.ceil

class ReserveFragment : BindingFragment<FragmentReserveBinding>(R.layout.fragment_reserve) {
    private lateinit var bannerJob: Job
    private var bannerPosition = 0
    private val bannerItems = ArrayList<ReserveBannerEntity>()
    private val menuListItems = mutableListOf<ReserveMenuListItem>()
    private val reserveGspayViewModel by viewModels<ReserveGspayViewModel>()
    private val reserveCategoryViewModel by viewModels<ReserveCategoryViewModel>()

    override fun initView() {
        initObserveGspay()
        initObserveCategory()

        setBannerPositionText()
        updateBannerPosition()

        setMenuListItems()
        initMenuListAdapter()

        moveToTop()
        initObserveReserveEvent()
    }

    private fun initObserveGspay() {
        reserveGspayViewModel.gspayState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    setDiscountTitle(state.data)
                    initDiscountMenuAdapter(state.data)
                    initBannerAdapter(state.data)
                }

                else -> Unit
            }
        }
    }

    private fun initObserveCategory() {
        reserveCategoryViewModel.categoryState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    setCategoryTitle(state.data)
                    initMenuCategoryTopAdapter(state.data)
                    initMenuCategoryBottomAdapter(state.data)
                }

                else -> Unit
            }
        }
    }

    private fun initBannerAdapter(data: ResponseReserveGspayDto) {
        data.topBanners.forEachIndexed { index, imageurl ->
            bannerItems.add(ReserveBannerEntity(imageurl, index + 1))
        }
        binding.vpReserveBanner.adapter = ReserveBannerAdapter(bannerItems)
        binding.vpReserveBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private fun setBannerPositionText() {
        bannerPosition = Int.MAX_VALUE / 2 - ceil(bannerItems.size.toDouble() / 2).toInt()
        binding.vpReserveBanner.setCurrentItem(bannerPosition, false)
        binding.tvReservePages.text = getString(R.string.reserve_top_banner, 1, bannerItems.size)
    }

    private fun updateBannerPosition() {
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
                        if (!bannerJob.isActive) scrollJobCreate()
                    }

                    ViewPager2.SCROLL_STATE_DRAGGING -> bannerJob.cancel()
                }
            }
        })
    }

    private fun setMenuListItems() {
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

    private fun initMenuListAdapter() {
        val menuListAdapter = GridMenuListAdapter(requireContext(), menuListItems)
        binding.gvReserveMenuList.adapter = menuListAdapter
    }

    private fun setDiscountTitle(data: ResponseReserveGspayDto) {
        binding.tvReserveDiscountTitle.text = data.headerTitle
        binding.tvReserveDiscountDate.text = data.date
    }

    private fun initDiscountMenuAdapter(data: ResponseReserveGspayDto) {
        val discountMenuAdapter = ReserveDiscountAdapter()
        binding.rcReserveDisountMenu.adapter = discountMenuAdapter
        discountMenuAdapter.setDiscountMenuList(data.products)
    }

    private fun setCategoryTitle(data: List<ResponseReserveCategoryDto>) {
        binding.tvReserveCategoryTopTitle.text = data[0].category
        binding.tvReserveCategoryBottomTitle.text = data[1].category
    }

    private fun initMenuCategoryTopAdapter(data: List<ResponseReserveCategoryDto>) {
        val menuCategoryAdapter = ReserveCategoryTopAdapter(
            requireContext(),
            data[0].products,
            onItemClicked = { id -> navigateToImageDetailFragment(id) }
        )
        binding.gvReserveCategoryTopMenu.adapter = menuCategoryAdapter
    }

    private fun navigateToImageDetailFragment(it: Int) {
        findNavController().navigate(
            R.id.fragment_purchase_detail,
            bundleOf(USER_ID to it),
        )
    }

    private fun initMenuCategoryBottomAdapter(data: List<ResponseReserveCategoryDto>) {
        val menuCategoryBottomAdapter =
            ReserveCategoryBottomAdapter(
                requireContext(),
                data[1].products,
                onItemClicked = { navigateToImageDetailFragment(it) },
            )
        binding.gvReserveCategoryBottomMenu.adapter = menuCategoryBottomAdapter
    }

    private fun initObserveReserveEvent() {
        gspayViewModel.reserveEventState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    handleReserveEventUi(state.data)
                }

                is UiState.Failure -> {
                    snackBar(binding.root, state.errorMessage)
                    Log.d("ReserveFragment", "initObserveReserveEvent: ${state.errorMessage}")
                }

                else -> Unit
            }
        }
    }

    private fun handleReserveEventUi(data: ResponseReserveEventDto) = with(binding) {
        tvReserveEventTitle.text = data.headerTitle
        tvReserveEventDate.text = data.date
        ivReserveEventMenuImage.load(data.products.first().image)
        clickReserveEventImage(data.products.first().productId)
    }

    private fun clickReserveEventImage(productId: Int) {
        binding.ivReserveEventMenuImage.setOnClickListener {
            findNavController().navigate(
                R.id.fragment_purchase_detail,
                bundleOf(USER_ID to productId),
            )
        }
    }

    private fun moveToTop() {
        binding.fabReserveButton.setOnClickListener {
            binding.svReserve.smoothScrollTo(0, 0)
        }
    }

    fun scrollJobCreate() {
        bannerJob = lifecycleScope.launch {
            delay(1500)
            binding.vpReserveBanner.setCurrentItem(++bannerPosition, true)
        }
    }

    override fun onResume() {
        super.onResume()
        scrollJobCreate()
    }

    override fun onPause() {
        super.onPause()
        bannerJob.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bannerJob.cancel()
        bannerItems.clear()
    }
}
