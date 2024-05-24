package com.sopt.now.gs.feature.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
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
    var monthCurrentPosition = 3
    var monthEventJob: Job? = null

    override fun initView() {
        topBannerAdapter = HomeBannerAdapter()
        bottomBannerAdapter = HomeBannerAdapter()
        rightMonthEventAdapter = HomeMonthEventAdapter()
        getImage()
        initPreReservationBtnClickListener()
        initHomeStateObserver()
        observeSelectedImage()
    }

    private fun getImage() {
        homeViewModel.getHomeImages()
    }

    private fun initPreReservationBtnClickListener() {
        binding.ivHomeReserveGs25PreReservation.setOnClickListener {
            findNavController().navigate(
                R.id.fragment_reserve
            )
        }
    }

    private fun initHomeStateObserver() {
        homeViewModel.homeState.observe(viewLifecycleOwner) {
            initTopBanner()
            initBottomBanner()
            initRightMonthEvent()
        }
    }

    private fun initTopBanner() {
        binding.vpHomeTopBanner.adapter = topBannerAdapter
        topBannerAdapter?.submitList(homeViewModel.homeState.value?.topBanners)

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
                delay(1000)
                if (topBannerCurrentPosition == 3) {
                    binding.vpHomeTopBanner.setCurrentItem(0, true)
                } else {
                    binding.vpHomeTopBanner.setCurrentItem(topBannerCurrentPosition++, true)
                }
            }
        }
    }

    private fun initBottomBanner() {
        binding.vpHomeBottomBanner.adapter = bottomBannerAdapter
        bottomBannerAdapter?.submitList(homeViewModel.homeState.value?.bottomBanners)

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
                delay(1000)
                if (bottomBannerCurrentPosition == 3) {
                    binding.vpHomeBottomBanner.setCurrentItem(0, true)
                } else {
                    binding.vpHomeBottomBanner.setCurrentItem(bottomBannerCurrentPosition++, true)
                }
            }
        }
    }

    private fun initRightMonthEvent() {
        val tempList = homeViewModel.homeState.value?.monthlyEvents?.subBanners
        val t = (tempList?.plus(tempList))?.toList()

        binding.rvHomeRightMonthEvent.adapter = rightMonthEventAdapter
        rightMonthEventAdapter?.submitList(t)

        binding.vpHomeLeftMonthEvent.load(
            homeViewModel.homeState.value?.monthlyEvents?.mainBanners?.get(
                1
            )
        )

        if (binding.rvHomeRightMonthEvent.onFlingListener == null) {
            val homeSnapHelper = LinearSnapHelper().apply {
                attachToRecyclerView(binding.rvHomeRightMonthEvent)
            }

            rightMonthEventAdapter?.let { onScrollStateChanged(homeSnapHelper, it) }
        }
    }

    private fun onScrollStateChanged(
        homeSnapHelper: LinearSnapHelper,
        homeMonthEventAdapter: HomeMonthEventAdapter,
    ) {
        binding.rvHomeRightMonthEvent.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val centerView = homeSnapHelper.findSnapView(layoutManager)
                    centerView?.run {
                        val pos = layoutManager.getPosition(this)
                        homeViewModel.updateLeftMonthEventImage(pos % 3)
                    }
                }
            }
        })

        if (!monthEventJob?.isActive!!) initMonthEventJob()
    }

    private fun initMonthEventJob() {
        monthEventJob = lifecycleScope.launch {
            while (true) {
                if ((monthCurrentPosition % 3) == 5) {
                    delay(1500)
                    binding.rvHomeRightMonthEvent.smoothScrollToPosition(monthCurrentPosition)
                    monthCurrentPosition = 3
                } else {
                    delay(1500)
                    binding.rvHomeRightMonthEvent.smoothScrollToPosition(monthCurrentPosition++)
                }
            }
        }
    }

    private fun observeSelectedImage() {
        homeViewModel.leftMonthEventResource.observe(viewLifecycleOwner) {
            binding.vpHomeLeftMonthEvent.load(
                homeViewModel.leftMonthEventResource.value?.let { it ->
                    homeViewModel.homeState.value?.monthlyEvents?.mainBanners?.get(
                        it
                    )
                }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        initMonthEventJob()
    }

    override fun onPause() {
        super.onPause()
        monthEventJob?.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        topBannerAdapter = null
        bottomBannerAdapter = null
        rightMonthEventAdapter = null

        topBannerJob?.cancel()
        bottomBannerJob?.cancel()
    }
}
