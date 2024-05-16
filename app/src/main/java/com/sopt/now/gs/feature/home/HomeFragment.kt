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
    private var adapter: HomeBannerAdapter? = null
    var currentPosition = 0
    var job: Job? = null
    override fun initView() {
        adapter = HomeBannerAdapter()

        initPreReservationBtnClickListener()
        initTopBanner()
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
        binding.vpMainTopBanner.adapter = adapter
        adapter?.submitList(itemList)

        binding.vpMainTopBanner.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPosition = position
            }
        })
        initJob()
    }

    private fun initJob() {
        job = lifecycleScope.launch {
            while (true) {
                delay(2000)
                if (currentPosition == 3) {
                    binding.vpMainTopBanner.setCurrentItem(0, true)
                } else {
                    binding.vpMainTopBanner.setCurrentItem(currentPosition++, true)
                }
            }
        }
    }
}
