package com.chanho.basic.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chanho.basic.R
import com.chanho.basic.databinding.ActivityMain2Binding
import com.chanho.basic.ui.home.HomeFragment
import com.chanho.basic.ui.setting.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Main2Activity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMain2Binding
    private val viewModel:Main2ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        binding.homeViewpager.adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        binding.homeBottomNavigationview.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.page_home -> {
                binding.homeViewpager.currentItem=0
                return true
            }
            R.id.page_setting -> {
                binding.homeViewpager.currentItem=1
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
    ):FragmentStateAdapter(fragmentManager,lifecycle){
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0->HomeFragment()
                1->SettingFragment()
                else -> error("No Fragment")
            }
        }

    }
}