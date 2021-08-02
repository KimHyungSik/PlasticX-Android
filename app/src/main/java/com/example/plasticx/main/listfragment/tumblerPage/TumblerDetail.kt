package com.example.plasticx.main.listfragment.tumblerPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.plasticx.R
import com.example.plasticx.model.TumblerItem

class TumblerDetail : AppCompatActivity() {

    val TAG = "TumblerDetail - 로그"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tumbler_detail)

        val tumblerItem:TumblerItem? = intent.getSerializableExtra("tumblerData") as TumblerItem?

        Log.d(TAG, "onCreate: $tumblerItem")
    }
}