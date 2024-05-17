package com.sopt.now.gs.feature.pay

import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.databinding.FragmentPayBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlin.math.ceil

class PayFragment : BindingFragment<FragmentPayBinding>(R.layout.fragment_pay) {
    private var bannerPosition = 0
    private lateinit var job: Job

    override fun initView() {
        val list: ArrayList<DataPage> = ArrayList<DataPage>().let {
            it.apply {
                add(DataPage(android.R.color.holo_red_light, "1 Page"))
                add(DataPage(android.R.color.holo_orange_dark, "2 Page"))
                add(DataPage(android.R.color.holo_green_dark, "3 Page"))
                add(DataPage(android.R.color.holo_blue_light, "4 Page"))
                add(DataPage(android.R.color.holo_blue_bright, "5 Page"))
                add(DataPage(android.R.color.black, "6 Page"))
            }
        }

        val viewPagerAdpater = ViewPagerAdpater()
        binding.viewPager.adapter = viewPagerAdpater
        viewPagerAdpater.submitList(list)

        binding.txtCurrentBanner.text = "1 / ${list.size}"

        bannerPosition = Int.MAX_VALUE / 2 - ceil(list.size.toDouble() / 2).toInt()

        binding.viewPager.setCurrentItem(bannerPosition, false)

        //  기본
        val pageMarginPx = 100
        val pagerWidth = 300
        val screenWidth = resources.displayMetrics.widthPixels // 스마트폰의 너비 길이를 가져옴
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.viewPager.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        binding.viewPager.offscreenPageLimit = 2 // 미리 로딩할 페이지 수

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            // 사용자가 스크롤 했을때 position 수정
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bannerPosition = position
                binding.txtCurrentBanner.text = "${(bannerPosition % list.size) + 1} / ${list.size}"
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (state) {
                    ViewPager2.SCROLL_STATE_IDLE -> {
                        if (!job.isActive) scrollJobCreate()
                    }

                    ViewPager2.SCROLL_STATE_DRAGGING -> {}

                    ViewPager2.SCROLL_STATE_SETTLING -> {}
                }
            }
        })
    }

    fun scrollJobCreate() {
        job = lifecycleScope.launchWhenResumed {
            delay(1500)
            binding.viewPager.setCurrentItem(++bannerPosition, true)
        }
    }

    override fun onResume() {
        super.onResume()
        scrollJobCreate()
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }
}
