package com.sopt.now.gs.feature.purchasedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.gs.core.view.UiState
import com.sopt.now.gs.data.api.ApiFactory
import com.sopt.now.gs.data.request.RequestLikedDto
import com.sopt.now.gs.data.response.ResponsePurchaseDetailDto
import kotlinx.coroutines.launch

class PurchaseDetailViewModel : ViewModel() {
    private val gsHometownService by lazy { ApiFactory.ServicePool.gsHometownService }
    private val _purchaseDetailState =
        MutableLiveData<UiState<ResponsePurchaseDetailDto>>()
    val purchaseDetailState: LiveData<UiState<ResponsePurchaseDetailDto>> get() = _purchaseDetailState

    private val _heartState = MutableLiveData<UiState<Boolean>>()
    val heartState: LiveData<UiState<Boolean>> get() = _heartState

    fun getPurchaseDetail(productId: Long) {
        viewModelScope.launch {
            runCatching {
                gsHometownService.getPurchaseDetail(productId)
            }.onSuccess { response ->
                val data = response.body()?.data
                data?.run { _purchaseDetailState.value = UiState.Success(this) }
            }.onFailure {
                _purchaseDetailState.value = UiState.Failure(it.message.toString())
            }
        }
    }

    fun postLiked(productId: Long) {
        viewModelScope.launch {
            runCatching {
                gsHometownService.postLiked(RequestLikedDto(productId))
            }.onSuccess { response ->
                val data = response.body()?.data
                data?.run { _heartState.value = UiState.Success(this.isLiked) }
            }.onFailure {
                _heartState.value = UiState.Failure(it.message.toString())
            }
        }
    }

    fun deleteLiked(productId: Long) {
        viewModelScope.launch {
            runCatching {
                gsHometownService.deleteLiked(RequestLikedDto(productId))
            }.onSuccess { response ->
                val data = response.body()?.data
                data?.run { _heartState.value = UiState.Success(this.isLiked) }
            }.onFailure {
                _heartState.value = UiState.Failure(it.message.toString())
            }
        }
    }
}
