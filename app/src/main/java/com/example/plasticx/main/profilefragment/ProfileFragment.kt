package com.example.plasticx.main.profilefragment

import com.example.plasticx.base.BaseFragment
import com.example.plasticx.databinding.ProfileFragmentBinding

class ProfileFragment : BaseFragment<ProfileFragmentBinding>(){
    override fun getViewBinding(): ProfileFragmentBinding = ProfileFragmentBinding.inflate(layoutInflater)
}