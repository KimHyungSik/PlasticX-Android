package com.example.plasticx.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.plasticx.base.BaseLoginActivity
import com.example.plasticx.databinding.ActivityLoginBinding
import com.example.plasticx.databinding.ActivityRegistrationBinding

class RegistrationActivity : BaseLoginActivity<ActivityRegistrationBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityRegistrationBinding
            = ActivityRegistrationBinding::inflate

    override fun setup() {
    }

}