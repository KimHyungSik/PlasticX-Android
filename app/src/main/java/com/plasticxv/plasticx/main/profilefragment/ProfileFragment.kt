package com.plasticxv.plasticx.main.profilefragment

import android.annotation.SuppressLint
import android.util.Log
import com.bumptech.glide.Glide
import com.plasticxv.plasticx.R
import com.plasticxv.plasticx.base.BaseFragment
import com.plasticxv.plasticx.login.LoginActivity
import com.plasticxv.plasticx.main.MainActivity
import com.plasticxv.plasticx.retrofit.repository.RetrofitRepository
import com.plasticxv.plasticx.user.UserManagerObject
import com.plasticxv.plasticx.utils.LOGIN_STATE
import com.plasticxv.plasticx.utils.PreferencesManager
import com.plasticxv.plasticx.utils.Utility.USER_ID_KEY
import com.kakao.sdk.user.UserApiClient
import com.plasticxv.plasticx.databinding.ProfileFragmentBinding

class ProfileFragment : BaseFragment<ProfileFragmentBinding>() {
    private val TAG = "ProfileFragment - 로그"
    override fun getViewBinding(): ProfileFragmentBinding =
        ProfileFragmentBinding.inflate(layoutInflater)

    @SuppressLint("CheckResult")
    override fun setUpViews() {
        Log.d(TAG, "setUpViews: ")

        val profileModel = ProfileModel("님", "0원")
        binding.profileModel = profileModel

        // 카카오 정보로 프로필 생성
        if (UserManagerObject.loginState == LOGIN_STATE.KAKAO) {
            UserApiClient.instance.me { user, error ->
                if (error != null) {
                    Log.e(TAG, "사용자 정보 요청 실패", error)
                } else if (user != null) {
                    profileModel.name = user.kakaoAccount?.profile?.nickname + " 님"
                    Glide
                        .with(activity?.applicationContext!!)
                        .load(user.kakaoAccount?.profile?.thumbnailImageUrl)
                        .centerCrop()
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(binding.avatarImageView)
                    RetrofitRepository().getUserRxInfo(UserManagerObject.userId)
                        .subscribe {
                            val body = it.asJsonObject
                            profileModel.deposit = body.get("deposit").asString + "원"
                            binding.profileModel = profileModel
                        }.isDisposed
                }
            }
        } else {
            RetrofitRepository().getUserRxInfo(UserManagerObject.userId)
                .subscribe {
                    val body = it.asJsonObject
                    profileModel.name = body.get("name").asString + " 님"
                    profileModel.deposit = body.get("deposit").asString + "원"
                    binding.profileModel = profileModel
                }.isDisposed
        }

        binding.logOutView.setOnClickListener {
            if (UserManagerObject.loginState == LOGIN_STATE.KAKAO) {
                // 카카오 로그아웃
                UserApiClient.instance.logout { error ->
                    if (error != null) {
                        Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                    } else {
                        logOut()
                    }
                }
            } else if (UserManagerObject.loginState == LOGIN_STATE.LOCAL) {
                logOut()
            }
        }

    }

    private fun logOut() {
        PreferencesManager.setString(activity?.application!!, USER_ID_KEY, "")
        UserManagerObject.setUpUser("", LOGIN_STATE.NO)
        (activity as MainActivity).moveIntentAllClear(LoginActivity::class.java)
    }
}