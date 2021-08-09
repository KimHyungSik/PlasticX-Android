package com.example.plasticx.loading

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.plasticx.databinding.DialogLottieLoadingBinding
class LottieLoadingDialog(context: Context): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DialogLottieLoadingBinding.inflate(layoutInflater).root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}