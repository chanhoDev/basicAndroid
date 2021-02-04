package com.chanho.basic.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.chanho.basic.R
import com.chanho.basic.databinding.ActivityMain2Binding
import com.chanho.basic.ui.home.HomeFragment
import com.chanho.basic.ui.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Main2Activity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMain2Binding
    private val viewModel: Main2ViewModel by viewModels()
    private val slideUpAnim by lazy {
        AnimationUtils.loadAnimation(this, R.anim.slide_up)
    }
    private val slideDownAnim by lazy {
        AnimationUtils.loadAnimation(this, R.anim.slide_down)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        binding.vm = viewModel
        binding.homeViewpager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.homeBottomNavigationview.setOnNavigationItemSelectedListener(this)
        binding.homeViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            var oldPosition: MenuItem? = null
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                oldPosition?.isChecked = false
                oldPosition = binding.homeBottomNavigationview.menu[position]
                oldPosition?.isChecked = true
            }
        })
//        viewModel.setBottomNavigationVisible(binding.homeBottomNavigationview.isVisible)
        onObserve()
    }

    private fun onObserve() {
//        viewModel.bottomNavigationVisible.observe(this) { isVisible ->
//            binding.homeBottomNavigationview.isVisible = isVisible
//        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.page_home -> {
                binding.homeViewpager.currentItem = 0
                return true
            }
            R.id.page_movie -> {
                binding.homeViewpager.currentItem = 1
                return true
            }
            R.id.page_setting -> {
                binding.homeViewpager.currentItem = 2
                return true
            }
            else -> {
                return false
            }
        }
    }

    private inner class ViewPagerAdapter(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle
    ) : FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> HomeFragment()
                1 -> HomeFragment()
                2 -> SettingFragment()
                else -> error("No Fragment")
            }
        }


    }
}