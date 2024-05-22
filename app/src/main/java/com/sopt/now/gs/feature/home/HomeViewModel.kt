package com.sopt.now.gs.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.gs.R

class HomeViewModel : ViewModel() {
    private val _leftMonthEventResource = MutableLiveData<Int>()
    val leftMonthEventResource : LiveData<Int> get() = _leftMonthEventResource

    fun updateLeftMonthEventImage(resource:Int){
        _leftMonthEventResource.value = resource
    }

    val leftEventImage: List<HomeBanner> = listOf(
        HomeBanner(R.drawable.img_home_month_event1),
        HomeBanner(R.drawable.img_home_month_event2),
        HomeBanner(R.drawable.img_home_month_event3),
        HomeBanner(R.drawable.img_home_month_event1),
        HomeBanner(R.drawable.img_home_month_event2),
        HomeBanner(R.drawable.img_home_month_event3)
        )

    val rightEventImage: List<HomeBanner> = listOf(
        HomeBanner(R.drawable.img_home_month_event_banner1),
        HomeBanner(R.drawable.img_home_month_event_banner2),
        HomeBanner(R.drawable.img_home_month_event_banner3),
        HomeBanner(R.drawable.img_home_month_event_banner1),
        HomeBanner(R.drawable.img_home_month_event_banner2),
        HomeBanner(R.drawable.img_home_month_event_banner3)
    )
}