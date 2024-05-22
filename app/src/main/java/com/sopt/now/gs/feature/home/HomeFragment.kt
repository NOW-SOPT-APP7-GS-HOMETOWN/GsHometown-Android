package com.sopt.now.gs.feature.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.viewpager2.widget.ViewPager2
import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.databinding.FragmentHomeBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel by viewModels<HomeViewModel>()
    private var topBannerAdapter: HomeBannerAdapter? = null
    private var bottomBannerAdapter: HomeBannerAdapter? = null
    private var rightMonthEventAdapter: HomeMonthEventAdapter? = null

    var topBannerCurrentPosition = 0
    var topBannerJob: Job? = null
    var bottomBannerCurrentPosition = 0
    var bottomBannerJob: Job? = null

    var rightMonthEventCurrentPosition = 0
    var rightMonthEventJob: Job? = null

    override fun initView() {
        topBannerAdapter = HomeBannerAdapter()
        bottomBannerAdapter = HomeBannerAdapter()
        rightMonthEventAdapter = HomeMonthEventAdapter()
        initPreReservationBtnClickListener()
        initTopBanner()
        initBottomBanner()
        initLeftMonthEvent()
        initRightMonthEvent()
    }

    private fun initPreReservationBtnClickListener() {
        binding.ivHomeReserveGs25PreReservation.setOnClickListener {
            findNavController().navigate(
                R.id.fragment_reserve
            )
        }
    }

    private fun initTopBanner() {
        val itemList: List<HomeBanner> = listOf(
            HomeBanner(R.drawable.img_home_top_banner),
            HomeBanner(R.drawable.img_home_top_banner2),
            HomeBanner(R.drawable.img_home_top_banner3)
        )
        binding.vpHomeTopBanner.adapter = topBannerAdapter
        topBannerAdapter?.submitList(itemList)

        binding.vpHomeTopBanner.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                topBannerCurrentPosition = position
            }
        })
        initTopBannerJob()
    }

    private fun initTopBannerJob() {
        topBannerJob = lifecycleScope.launch {
            while (true) {
                delay(2000)
                if (topBannerCurrentPosition == 3) {
                    binding.vpHomeTopBanner.setCurrentItem(0, true)
                } else {
                    binding.vpHomeTopBanner.setCurrentItem(topBannerCurrentPosition++, true)
                }
            }
        }
    }

    private fun initBottomBanner() {
        val bottomItemList: List<HomeBanner> = listOf(
            HomeBanner(R.drawable.img_home_bottom_banner),
            HomeBanner(R.drawable.img_home_bottom_banner2),
            HomeBanner(R.drawable.img_home_bottom_banner3)
        )
        binding.vpHomeBottomBanner.adapter = bottomBannerAdapter
        bottomBannerAdapter?.submitList(bottomItemList)

        binding.vpHomeBottomBanner.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomBannerCurrentPosition = position
            }
        })
        initBottomBannerJob()
    }

    private fun initBottomBannerJob() {
        bottomBannerJob = lifecycleScope.launch {
            while (true) {
                delay(2000)
                if (bottomBannerCurrentPosition == 3) {
                    binding.vpHomeBottomBanner.setCurrentItem(0, true)
                } else {
                    binding.vpHomeBottomBanner.setCurrentItem(bottomBannerCurrentPosition++, true)
                }
            }
        }
    }

    private fun initLeftMonthEvent() {
        homeViewModel.leftMonthEventResource.observe(viewLifecycleOwner) {
            binding.vpHomeLeftMonthEvent.setImageResource(homeViewModel.leftEventImage[it].image)
        }
    }

    private fun initRightMonthEvent() {
        val eventList: List<HomeBanner> = listOf(
            HomeBanner(R.drawable.img_home_month_event_banner1),
            HomeBanner(R.drawable.img_home_month_event_banner2),
            HomeBanner(R.drawable.img_home_month_event_banner3),
            HomeBanner(R.drawable.img_home_month_event_banner1),
            HomeBanner(R.drawable.img_home_month_event_banner2),
            HomeBanner(R.drawable.img_home_month_event_banner3)
        )
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.vpHomeRightMonthEvent)

        binding.vpHomeRightMonthEvent.adapter = rightMonthEventAdapter
        rightMonthEventAdapter?.submitList(eventList)


        rightMonthEventJob = lifecycleScope.launch {
            while (true) {
                delay(1000)
                if (rightMonthEventCurrentPosition == 4) {
                    binding.vpHomeRightMonthEvent.smoothScrollToPosition(0)
                    rightMonthEventCurrentPosition = 0
                    homeViewModel.updateLeftMonthEventImage(rightMonthEventCurrentPosition)
                } else {
                    binding.vpHomeRightMonthEvent.smoothScrollToPosition(rightMonthEventCurrentPosition++)
                    homeViewModel.updateLeftMonthEventImage(rightMonthEventCurrentPosition)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        topBannerAdapter = null
        bottomBannerAdapter = null
        rightMonthEventAdapter = null

        topBannerJob?.cancel()
        bottomBannerJob?.cancel()
        rightMonthEventJob?.cancel()
    }
}
