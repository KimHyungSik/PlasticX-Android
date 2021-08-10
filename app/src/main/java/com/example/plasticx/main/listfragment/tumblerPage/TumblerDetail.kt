package com.example.plasticx.main.listfragment.tumblerPage

import android.annotation.SuppressLint
import android.graphics.Color
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
        val tumblerItem: TumblerItem = intent.getSerializableExtra("tumblerData") as TumblerItem

        binding.tumblerItem = tumblerItem

        if(!tumblerItem.status){
            binding.tumblerDetailBorrowStatus.text = "반납완료"
            binding.tumblerDetailBorrowStatusLayout.setCardBackgroundColor(Color.rgb(70, 58, 62))
            binding.tumblerDetailBorrowStatusLayout.setCardBackgroundColor(Color.rgb(204,204,204))
        }

        binding.tumblerDetailBackArrow.setOnClickListener { onBackPressed() }

    }
}