package com.example.plasticx.main.honefragment

import com.example.plasticx.base.BaseFragment
import com.example.plasticx.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment<HomeFragmentBinding>(){
    override fun getViewBinding(): HomeFragmentBinding = HomeFragmentBinding.inflate(layoutInflater)
}