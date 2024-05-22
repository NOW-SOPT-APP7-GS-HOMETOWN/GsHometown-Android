package com.sopt.now.gs.feature.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.gs.R
import com.sopt.now.gs.data.api.ApiFactory
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _leftMonthEventResource = MutableLiveData<Int>()
    val leftMonthEventResource: LiveData<Int> get() = _leftMonthEventResource

    val gsService by lazy { ApiFactory.ServicePool }

    fun getHomeImage() {
        viewModelScope.launch {
            runCatching { gsService.userService.getHomeImages() }
                .onSuccess {
                    Log.d("asd", it.data.toString())
                }
                .onFailure { Log.d("asd", it.message.toString()) }
        }
    }


    fun updateLeftMonthEventImage(resource: Int) {
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