package com.sopt.now.gs.feature.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.gs.data.api.ApiFactory
import com.sopt.now.gs.data.response.ResponseHomeDto
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _homeState = MutableLiveData<ResponseHomeDto?>()
    val homeState: MutableLiveData<ResponseHomeDto?> get() = _homeState

    private val _leftMonthEventResource = MutableLiveData<Int>()
    val leftMonthEventResource: LiveData<Int> get() = _leftMonthEventResource

    private val gsService by lazy { ApiFactory.ServicePool }

    fun getHomeImages() {
        viewModelScope.launch {
            runCatching { gsService.gsHometownService.getHomeImages() }
                .onSuccess {
                    if (it.data != null) {
                        _homeState.value = it.data
                    }
                }
                .onFailure { Log.d("homeApi", it.message.toString()) }
        }
    }

    fun updateLeftMonthEventImage(resource: Int) {
        _leftMonthEventResource.value = resource

    }
}
