package com.sopt.now.gs.feature.home

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.sopt.now.gs.R
import com.sopt.now.gs.core.base.BindingFragment
import com.sopt.now.gs.databinding.FragmentHomeBinding
import com.sopt.now.gs.feature.util.KeyStorage

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initView() {
        initPreReservationBtnClickListener()
    }

    private fun navigateToReserve(id: Int) {
        findNavController().navigate(
            R.id.fragment_reserve,
            bundleOf(KeyStorage.USER_ID to id),
        )
    }

    private fun initPreReservationBtnClickListener() {
        binding.ivMainGs25PreReservation.setOnClickListener {
            findNavController().navigate(
                R.id.fragment_reserve
            )
        }
    }
}
