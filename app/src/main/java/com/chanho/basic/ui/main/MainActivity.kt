package com.chanho.basic.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.chanho.basic.R
import com.chanho.basic.databinding.ActivityMainBinding
import com.chanho.basic.ui.detail.DetailFragment
import com.chanho.basic.ui.favorite.FavoriteFragment
import com.chanho.basic.ui.home.HomeFragment
import com.chanho.basic.ui.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val slideUpAnim by lazy {
        AnimationUtils.loadAnimation(this, R.anim.slide_up)
    }
    private val slideDownAnim by lazy {
        AnimationUtils.loadAnimation(this, R.anim.slide_down)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
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
        onObserve()
    }

    private fun onObserve() {
        viewModel.filterClicked.observe(this) {

        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.page_home -> {
                binding.homeViewpager.currentItem = 0
                return true
            }
            R.id.page_favorite -> {
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
                1 -> FavoriteFragment()
                2 -> SettingFragment()
                else -> error("No Fragment")
            }
        }


    }
}