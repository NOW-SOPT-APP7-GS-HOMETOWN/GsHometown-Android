package com.sopt.now.gs.feature.purchasedetail

import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.databinding.FragmentPurchaseDetailBinding

class PurchaseDetailFragment :
    BindingFragment<FragmentPurchaseDetailBinding>(R.layout.fragment_purchase_detail) {
    override fun initView() {
        initHeartBtnClickListener()
        initFabBtnClickListener()
    }

    private fun initHeartBtnClickListener() {
        binding.btnPurchaseDetailHeart.setOnClickListener { it.isSelected = !it.isSelected }
    }

    private fun initFabBtnClickListener() = with(binding) {
        btnPurchaseDetailFabUp.setOnClickListener { svPurchaseDetail.smoothScrollTo(0, 0) }
    }
}
