package com.example.plasticx.main.morefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plasticx.base.BaseFragment
import com.example.plasticx.databinding.MoreFragmentBinding

class MoreFragment : BaseFragment<MoreFragmentBinding>(){
    override fun getViewBinding(): MoreFragmentBinding  = MoreFragmentBinding.inflate(layoutInflater)
}