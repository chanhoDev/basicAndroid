package com.chanho.basic.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chanho.basic.R
import com.chanho.basic.databinding.FragmentHomeBinding
import com.chanho.basic.ui.detail.DetailFragment
import com.chanho.basic.ui.main.MainViewModel
import com.chanho.basic.util.RecyclerViewScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(inflater.context),
            R.layout.fragment_home,
            container,
            false
        )
        binding.lifecycleOwner = this
        onObserve()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        parentFragmentManager.beginTransaction().replace(R.id.home_container,HomeListFragment.newInstance()).commit()

    }

    private fun onObserve() {

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
            }
    }
}