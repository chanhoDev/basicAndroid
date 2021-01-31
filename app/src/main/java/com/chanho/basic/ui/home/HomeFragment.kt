package com.chanho.basic.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chanho.basic.R
import com.chanho.basic.databinding.FragmentHomeBinding
import com.chanho.basic.ui.main.Main2ViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: Main2ViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private val adapter: HomeAdapter by lazy {
        HomeAdapter(viewModel)
    }

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
        binding.homeRecyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.homeRecyclerview.adapter = adapter

    }

    private fun onObserve() {
//        viewModel.
        sharedViewModel.movieList.observe(viewLifecycleOwner, {
            Log.e("homeFragment!!", it.toString())
            adapter.onDeleteAll()
            adapter.onItemsAdd(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
            }
    }
}