package com.plasticxv.plasticx.main.honefragment

import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.plasticxv.plasticx.R
import com.plasticxv.plasticx.base.BaseFragment
import com.plasticxv.plasticx.databinding.HomeFragmentBinding
import java.lang.Math.abs

class HomeFragment : BaseFragment<HomeFragmentBinding>(){
    val TAG = "HomeFragment - 로그"
    override fun getViewBinding(): HomeFragmentBinding =
        HomeFragmentBinding.inflate(layoutInflater)

    override fun setUpViews(){
        Log.d(TAG, "setUpViews: ")
        val viewPager = binding.homeViewPager
        val models: MutableList<Int> = mutableListOf()

        // 이미지 데이터
        models.add(R.drawable.main_logo)
        models.add(R.drawable.logo_icon)
        models.add(R.drawable.logo_text)

        val adapter = ViewPagerAdapter(models, activity?.applicationContext!!)
        viewPager.adapter = adapter

        binding.dotsIndicator.setViewPager2(viewPager)

        // 좌우 미리보기 생성
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.viewpager_current_item_horizontal_margin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.viewpager_next_item_visible)
        val offsetPx = pageMarginPx + pagerWidth

        viewPager.offscreenPageLimit = 2

        // 좌우 미리보기 크기
        viewPager.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
            page.scaleY = 1 - (0.25f * abs(position))
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }
}