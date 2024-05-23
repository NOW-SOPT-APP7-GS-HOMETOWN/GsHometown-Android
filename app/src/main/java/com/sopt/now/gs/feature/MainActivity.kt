package com.sopt.now.gs.feature

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sopt.now.gs.R
import com.sopt.now.gs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMainBottomNavigation()
    }

    private fun initMainBottomNavigation() {
        val navController =
            (supportFragmentManager.findFragmentById(R.id.fcv_home) as NavHostFragment)
                .findNavController()
        binding.bnvHome.setupWithNavController(navController)
        setBottomNavigationVisibility(navController)
    }

    private fun setBottomNavigationVisibility(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bnvHome.visibility = when (destination.id) {
                R.id.fragment_purchase_detail -> View.GONE
                else -> View.VISIBLE
            }
            when (destination.id) {
                R.id.fragment_reserve -> {
                    binding.bnvHome.menu.findItem(R.id.fragment_pay)?.apply {
                        setFabBtnInVisible()
                    }
                }

                else -> {
                    binding.bnvHome.menu.findItem(R.id.fragment_pay)?.apply {
                        setFabBtnVisible()
                    }
                }
            }
        }
    }

    private fun MenuItem.setFabBtnVisible() {
        title = ""
        icon = null
        isEnabled = false
        binding.clMainFab.visibility = View.VISIBLE
        binding.tvMainFabButton.visibility = View.VISIBLE
    }

    private fun MenuItem.setFabBtnInVisible() {
        setTitle(R.string.menu_item_pay)
        setIcon(R.drawable.ic_nav_pay_grey_24)
        isEnabled = true
        binding.clMainFab.visibility = View.GONE
        binding.tvMainFabButton.visibility = View.GONE
    }
}
