package com.sopt.now.gs.feature.reserve

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.gs.core.view.UiState
import com.sopt.now.gs.data.api.ApiFactory
import com.sopt.now.gs.data.response.ResponseReserveCategoryDto
import kotlinx.coroutines.launch

class ReserveCategoryViewModel : ViewModel() {
    private val _categoryState = MutableLiveData<UiState<List<ResponseReserveCategoryDto>>>()
    val categoryState: LiveData<UiState<List<ResponseReserveCategoryDto>>> get() = _categoryState

    init{
        getCategory()
    }

    fun getCategory() {
        viewModelScope.launch {
            runCatching { ApiFactory.ServicePool.gsHometownService.getReserveCategory("category") }
                .onSuccess { response ->
                    val data = response.body()?.data
                    data?.run { _categoryState.value = UiState.Success(this) }
                }
                .onFailure {
                    _categoryState.value = UiState.Failure("error")
                }
        }
    }
}