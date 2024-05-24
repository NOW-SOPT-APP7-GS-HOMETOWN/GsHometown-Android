package com.sopt.now.gs.feature.reserve

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.gs.core.view.UiState
import com.sopt.now.gs.data.api.ApiFactory
import com.sopt.now.gs.data.response.ResponseReserveGspayDto
import kotlinx.coroutines.launch

class ReserveGspayViewModel : ViewModel() {
    private val _gspayState = MutableLiveData<UiState<ResponseReserveGspayDto>>()
    val gspayState: LiveData<UiState<ResponseReserveGspayDto>> get() = _gspayState

    init{
        getGspay()
    }

    fun getGspay() {
        viewModelScope.launch {
            runCatching { ApiFactory.ServicePool.gsHometownService.getReserveGspay("gspay") }
                .onSuccess { response ->
                    val data = response.body()?.data
                    data?.run { _gspayState.value = UiState.Success(this) }
                }
                .onFailure {
                    _gspayState.value = UiState.Failure("error")
                }
        }
    }
}