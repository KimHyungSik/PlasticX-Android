package com.plasticxv.plasticx.main.listfragment.tumblerPage

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import com.plasticxv.plasticx.base.BaseActivity
import com.plasticxv.plasticx.databinding.ActivityTumblerDetailBinding
import com.plasticxv.plasticx.model.TumblerItem


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