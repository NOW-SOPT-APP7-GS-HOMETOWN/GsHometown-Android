package com.sopt.now.gs.feature.reserve

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.gs.R
import com.sopt.now.gs.core.view.UiState
import com.sopt.now.gs.data.api.ApiFactory
import com.sopt.now.gs.data.response.ResponseReserveGspayDto
import kotlinx.coroutines.launch

class GspayViewModel : ViewModel() {
    private val _gspayState = MutableLiveData<UiState<ResponseReserveGspayDto>>()
    val gspayState: LiveData<UiState<ResponseReserveGspayDto>> get() = _gspayState

    fun getGspay() {
        viewModelScope.launch {
            runCatching { ApiFactory.ServicePool.gsHometownService.getGspay("gspay") }
                .onSuccess { response ->
                    val data = response.body()?.data
                    data?.run { _gspayState.value = UiState.Success(this) }
                    Log.d("gspay", data.toString())
                }
                .onFailure {
                    _gspayState.value = UiState.Failure("error")
                    Log.d("gspay", it.message.toString())
                }
        }
    }

}