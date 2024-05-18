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
    private var eventAdapter: HomeBannerAdapter? = null
    private var adapter: HomeMonthEventAdapter? = null

    var topBannerCurrentPosition = 0
    var topBannerJob: Job? = null
    var bottomBannerCurrentPosition = 0
    var bottomBannerJob: Job? = null
    var monthEventCurrentPosition = 0
    var monthEventJob: Job? = null
    var monthEventCurrentPosition2 = 0
    var monthEventJob2: Job? = null

    override fun initView() {
        topBannerAdapter = HomeBannerAdapter()
        bottomBannerAdapter = HomeBannerAdapter()
        eventAdapter = HomeBannerAdapter()
        adapter = HomeMonthEventAdapter()
        initPreReservationBtnClickListener()
        initTopBanner()
        initBottomBanner()
        initMonthEvent()
        initRecyclerView()
    }

    private fun initPreReservationBtnClickListener() {
        binding.ivMainGs25PreReservation.setOnClickListener {
            findNavController().navigate(
                R.id.fragment_reserve
            )
        }
    }

    private fun initTopBanner() {
        val itemList: List<HomeBanner> = listOf(
            HomeBanner(R.drawable.img_main_top_banner),
            HomeBanner(R.drawable.img_main_top_banner2),
            HomeBanner(R.drawable.img_main_top_banner3)
        )
        binding.vpMainTopBanner.adapter = topBannerAdapter
        topBannerAdapter?.submitList(itemList)

        binding.vpMainTopBanner.registerOnPageChangeCallback(object :
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
                    binding.vpMainTopBanner.setCurrentItem(0, true)
                } else {
                    binding.vpMainTopBanner.setCurrentItem(topBannerCurrentPosition++, true)
                }
            }
        }
    }

    private fun initBottomBanner() {
        val bottomItemList: List<HomeBanner> = listOf(
            HomeBanner(R.drawable.img_main_bottom_banner),
            HomeBanner(R.drawable.img_main_bottom_banner2),
            HomeBanner(R.drawable.img_main_bottom_banner3)
        )
        binding.vpMainBottomBanner.adapter = bottomBannerAdapter
        bottomBannerAdapter?.submitList(bottomItemList)

        binding.vpMainBottomBanner.registerOnPageChangeCallback(object :
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
                    binding.vpMainBottomBanner.setCurrentItem(0, true)
                } else {
                    binding.vpMainBottomBanner.setCurrentItem(bottomBannerCurrentPosition++, true)
                }
            }
        }
    }

    private fun initMonthEvent() {
        val EventItemList: List<HomeBanner> = listOf(
            HomeBanner(R.drawable.img_main_month_event1),
            HomeBanner(R.drawable.img_main_month_event2),
            HomeBanner(R.drawable.img_main_month_event3)
        )
        binding.vpMainMonthEvent1.adapter = eventAdapter
        eventAdapter?.submitList(EventItemList)

        binding.vpMainMonthEvent1.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                monthEventCurrentPosition = position
            }
        })
        initMonthEventJob()
    }

    private fun initMonthEventJob() {
        monthEventJob = lifecycleScope.launch {
            while (true) {
                delay(1000)
                if (monthEventCurrentPosition == 3) {
                    binding.vpMainMonthEvent1.setCurrentItem(0, true)
                } else {
                    binding.vpMainMonthEvent1.setCurrentItem(monthEventCurrentPosition++, true)
                }
            }
        }
    }

    private fun initRecyclerView() {
        //TODO
        val eventList: List<HomeBanner> = listOf(
            HomeBanner(R.drawable.img_main_month_event_banner1),
            HomeBanner(R.drawable.img_main_month_event_banner2),
            HomeBanner(R.drawable.img_main_month_event_banner3),
            HomeBanner(R.drawable.img_main_month_event_banner1),
            HomeBanner(R.drawable.img_main_month_event_banner2),
            HomeBanner(R.drawable.img_main_month_event_banner3)
        )
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.vpMainMonthEvent2)

        binding.vpMainMonthEvent2.adapter = adapter
        adapter?.submitList(eventList)


        monthEventJob2 = lifecycleScope.launch {
            while (true) {
                delay(1000)
                if (monthEventCurrentPosition2 == 4) {
                    binding.vpMainMonthEvent2.smoothScrollToPosition(0)
                    monthEventCurrentPosition2 = 0
                } else {
                    binding.vpMainMonthEvent2.smoothScrollToPosition(monthEventCurrentPosition2++)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
