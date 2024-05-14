package com.sopt.now.gs.feature.reserve

import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.databinding.FragmentReserveBinding
import com.sopt.now.gs.feature.util.KeyStorage

class ReserveFragment : BindingFragment<FragmentReserveBinding>(R.layout.fragment_reserve) {
    override fun initView() {
        val memberId = arguments?.getInt(KeyStorage.USER_ID) ?: -1
        if (memberId != -1) {
            binding.tvReserveExample.text = "홈에서 받아온 Member ID: $memberId"
        }
    }
}
