package com.example.plasticx.main.profilefragment

import android.util.Log
import com.bumptech.glide.Glide
import com.example.plasticx.R
import com.example.plasticx.base.BaseFragment
import com.example.plasticx.databinding.ProfileFragmentBinding
import com.example.plasticx.login.LoginActivity
import com.example.plasticx.main.MainActivity
import com.kakao.sdk.user.UserApiClient

class ProfileFragment : BaseFragment<ProfileFragmentBinding>() {
    val TAG = "ProfileFragment - 로그"
    override fun getViewBinding(): ProfileFragmentBinding =
        ProfileFragmentBinding.inflate(layoutInflater)

    override fun setUpViews() {

        // 카카오 정보로 프로필 생성
       UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
            } else if (user != null) {
                binding.userName.text = user.kakaoAccount?.profile?.nickname + " 님"
                Glide
                    .with(activity?.applicationContext!!)
                    .load(user.kakaoAccount?.profile?.thumbnailImageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_person_24)
                    .into(binding.avatarImageView)
            }
        }

        binding.logOutView.setOnClickListener {
            // 카카오 로그아웃
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                }
                else {
                    Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                    (activity as MainActivity).moveIntentAllClear(LoginActivity::class.java)
                }

            }
        }
    }
}