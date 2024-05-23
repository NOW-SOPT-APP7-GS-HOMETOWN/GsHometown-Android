package com.sopt.now.gs.feature.purchasedetail

import androidx.fragment.app.viewModels
import coil.load
import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.core.util.fragment.snackBar
import com.sopt.now.gs.core.view.UiState
import com.sopt.now.gs.data.response.ResponsePurchaseDetailDto
import com.sopt.now.gs.databinding.FragmentPurchaseDetailBinding
import com.sopt.now.gs.feature.util.KeyStorage.USER_ID
import com.sopt.now.gs.feature.util.PriceFormatter
import com.sopt.now.gs.feature.util.setOnDebouncedClickListener

class PurchaseDetailFragment :
    BindingFragment<FragmentPurchaseDetailBinding>(R.layout.fragment_purchase_detail) {
    private val viewModel by viewModels<PurchaseDetailViewModel>()
    private var productId = -1
    override fun initView() {
        initGetProductId()
        initObservePurchaseDetail()
        initHeartBtnClickListener()
        initFabBtnClickListener()
        observeHeartState()
    }

    private fun initGetProductId() {
        productId = arguments?.getInt(USER_ID) ?: -1
        if (productId != -1) {
            viewModel.getPurchaseDetail(productId.toLong())
        }
    }

    private fun initObservePurchaseDetail() {
        viewModel.purchaseDetailState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    handlePurchaseDetailUi(state.data)
                }

                is UiState.Failure -> snackBar(binding.root, state.errorMessage)

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
        btnPurchaseDetailHeart.isChecked = data.isLiked
    }

    private fun initHeartBtnClickListener() {
        /*binding.btnPurchaseDetailHeart.setOnDebounceClick()
            .debounce(1000) // 1초
            .flowWithLifecycle(viewLifeCycle)
            .onEach {
                if (binding.btnPurchaseDetailHeart.isChecked) {
                    viewModel.postLiked(productId.toLong())
                } else {
                    viewModel.deleteLiked(productId.toLong())
                }
            }.launchIn(viewLifeCycleScope)
*/
        binding.btnPurchaseDetailHeart.setOnDebouncedClickListener(viewLifecycleOwner) {
            if (binding.btnPurchaseDetailHeart.isChecked) {
                viewModel.postLiked(productId.toLong())
            } else {
                viewModel.deleteLiked(productId.toLong())
            }
        }
    }

    private fun observeHeartState() {
        viewModel.heartState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Failure -> {
                    snackBar(binding.root, state.errorMessage)
                }

                else -> Unit
            }
        }
    }

    private fun initFabBtnClickListener() = with(binding) {
        btnPurchaseDetailFabUp.setOnClickListener { svPurchaseDetail.smoothScrollTo(0, 0) }
    }
}
