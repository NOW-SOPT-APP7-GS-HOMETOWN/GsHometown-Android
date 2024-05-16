package com.sopt.now.gs.feature.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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

    var topBannerCurrentPosition = 0
    var topBannerJob: Job? = null
    var bottomBannerCurrentPosition = 0
    var bottomBannerJob: Job? = null
    override fun initView() {
        topBannerAdapter = HomeBannerAdapter()
        bottomBannerAdapter = HomeBannerAdapter()

        initPreReservationBtnClickListener()
        initTopBanner()
        initBottomBanner()
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
}
