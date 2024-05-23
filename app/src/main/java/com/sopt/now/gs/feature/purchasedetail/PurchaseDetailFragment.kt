package com.sopt.now.gs.feature.purchasedetail

import androidx.fragment.app.viewModels
import coil.load
import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.core.view.UiState
import com.sopt.now.gs.data.response.ResponsePurchaseDetailDto
import com.sopt.now.gs.databinding.FragmentPurchaseDetailBinding
import com.sopt.now.gs.feature.util.KeyStorage.USER_ID
import com.sopt.now.gs.feature.util.PriceFormatter

class PurchaseDetailFragment :
    BindingFragment<FragmentPurchaseDetailBinding>(R.layout.fragment_purchase_detail) {
    private val viewModel by viewModels<PurchaseDetailViewModel>()
    override fun initView() {
        initGetProductId()
        initObservePurchaseDetail()
        initHeartBtnClickListener()
        initFabBtnClickListener()
    }

    private fun initGetProductId() {
        val memberId = arguments?.getInt(USER_ID) ?: -1
        if (memberId != -1) {
            viewModel.getPurchaseDetail(memberId.toLong())
        }
    }

    private fun initObservePurchaseDetail() {
        viewModel.purchaseDetailState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    handlePurchaseDetailUi(state.data)
                }

                else -> Unit
            }
        }
    }

    private fun handlePurchaseDetailUi(data: ResponsePurchaseDetailDto) = with(binding) {
        ivPurchaseDetailTop.load(data.thumbnail)
        tvPurchaseDetailTitle.text = data.title
        tvPurchaseDetailPrice.text = PriceFormatter.formatPrice(data.price)
        tvPurchaseDetailReviewCount.text = "(${data.reviewCount})"
        ivPurchaseDetailMain.load(data.detailImage)
    }

    private fun initHeartBtnClickListener() {
        binding.btnPurchaseDetailHeart.setOnClickListener { it.isSelected = !it.isSelected }
    }

    private fun initFabBtnClickListener() = with(binding) {
        btnPurchaseDetailFabUp.setOnClickListener { svPurchaseDetail.smoothScrollTo(0, 0) }
    }
}
