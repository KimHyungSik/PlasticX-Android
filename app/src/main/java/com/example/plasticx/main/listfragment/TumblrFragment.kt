package com.example.plasticx.main.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plasticx.base.BaseFragment
import com.example.plasticx.databinding.TumblrFragmentBinding

class TumblrFragment : BaseFragment<TumblrFragmentBinding>() {
    override fun getViewBinding(): TumblrFragmentBinding  = TumblrFragmentBinding.inflate(layoutInflater)
}