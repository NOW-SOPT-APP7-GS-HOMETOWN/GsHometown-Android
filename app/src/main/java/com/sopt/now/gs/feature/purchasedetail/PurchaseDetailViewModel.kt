package com.sopt.now.gs.feature.purchasedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.gs.core.view.UiState
import com.sopt.now.gs.data.api.ApiFactory
import com.sopt.now.gs.data.response.ResponsePurchaseDetailDto
import kotlinx.coroutines.launch

class PurchaseDetailViewModel : ViewModel() {
    private val _purchaseDetailState =
        MutableLiveData<UiState<ResponsePurchaseDetailDto>>()
    val purchaseDetailState: LiveData<UiState<ResponsePurchaseDetailDto>> get() = _purchaseDetailState

    fun getPurchaseDetail(productId: Long) {
        viewModelScope.launch {
            runCatching {
                ApiFactory.ServicePool.gsHometownService.getPurchaseDetail(productId)
            }.onSuccess { response ->
                val data = response.body()?.data
                data?.run { _purchaseDetailState.value = UiState.Success(this) }
            }.onFailure {
                _purchaseDetailState.value = UiState.Failure("error")
            }
        }
    }
}
