package com.example.plasticx.main

import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.plasticx.R
import com.example.plasticx.base.BaseActivity
import com.example.plasticx.databinding.ActivityMainBinding
import com.example.plasticx.qr.QrActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    val TAG = "MainActivity - 로그"

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
            = ActivityMainBinding::inflate

    override fun setup() {
        setupViews()
        binding.bottomNavigationView.background = null
        // 가운데 비우기 위한 아이템 비활성화
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        val navHostFragment= supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

    private fun setupViews(){
        binding.mainBottomFab.setOnClickListener {
            moveIntent(QrActivity::class.java)
        }

    }
}