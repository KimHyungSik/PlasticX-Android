package com.example.plasticx.main.listfragment.tumblerPage

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.Fade
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.plasticx.R
import com.example.plasticx.base.BaseActivity
import com.example.plasticx.databinding.ActivityMainBinding
import com.example.plasticx.databinding.ActivityTumblerDetailBinding
import com.example.plasticx.model.TumblerItem


class TumblerDetail : BaseActivity<ActivityTumblerDetailBinding>() {

    val TAG = "TumblerDetail - 로그"

    override val bindingInflater: (LayoutInflater) -> ActivityTumblerDetailBinding =
        ActivityTumblerDetailBinding::inflate

    @SuppressLint("SetTextI18n")
    override fun setup() {
        val tumblerItem:TumblerItem = intent.getSerializableExtra("tumblerData") as TumblerItem

        Log.d(TAG, "onCreate: $tumblerItem")

        binding.tumblerDetailName.text = tumblerItem.tumblerName
        binding.tumblerDetailDate.text = "기간 : " + tumblerItem.tumblerStartDay + " ~ " + tumblerItem.tumblerEndDay
        binding.tumblerDetailFrom.text = "대여 장소 : " +  tumblerItem. tumblerFrom

//        val fade = Fade()
//        fade.excludeTarget(android.R.id.statusBarBackground, true)
//        fade.excludeTarget(android.R.id.navigationBarBackground, true)
//
//        window.enterTransition = fade
//        window.exitTransition = fade

        binding.tumblerDetailBackArrow.setOnClickListener { onBackPressed() }

    }
}