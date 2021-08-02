package com.example.plasticx.main

import android.content.Intent
import android.transition.Explode
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.room.Room
import com.example.plasticx.MyApplication
import com.example.plasticx.R
import com.example.plasticx.base.BaseActivity
import com.example.plasticx.dagger.di.AppComponent
import com.example.plasticx.databinding.ActivityMainBinding
import com.example.plasticx.model.NoticeModel
import com.example.plasticx.qr.QrActivity
import com.example.plasticx.room.RoomRepository
import com.example.plasticx.room.notice.NoticeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {

    val TAG = "MainActivity - 로그"
    private var lastTimeBackPressed: Long = 0

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    lateinit var mainComponent: AppComponent

    override fun setup() {
        setupViews()
        binding.bottomNavigationView.background = null
        // 가운데 비우기 위한 아이템 비활성화
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        // dagger
        mainComponent = (application as MyApplication).appComponent
        mainComponent.mainComponent().create().inject(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

    private fun setupViews() {
        binding.mainBottomFab.setOnClickListener {
            moveIntentResult(QrActivity::class.java)
        }
    }

    override fun resultActivity(activityResult: ActivityResult) {
        super.resultActivity(activityResult)
        if (activityResult.resultCode == RESULT_OK) {
            Toast.makeText(this, "텀블러 대여 성공", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBackPressed >= 1500) {
            lastTimeBackPressed = System.currentTimeMillis()
            Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG).show()
        } else {
            finish()
        }
    }
}