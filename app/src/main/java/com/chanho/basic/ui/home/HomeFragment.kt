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
import com.chanho.basic.ui.main.MainViewModel
import com.chanho.basic.util.RecyclerViewScrollListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
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
        binding.vm = viewModel
        onObserve()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.homeRecyclerview.layoutManager = linearLayoutManager
        binding.homeRecyclerview.adapter = adapter
        binding.homeRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                sharedViewModel.bottomNavigationVisible.value?.let { isVisible ->
//                    if ((dy < 0 || dy > 0) && isVisible) {
//                        sharedViewModel.setBottomNavigationVisible(!isVisible)
//                    }
//                }
                viewModel.homeSearchLayoutVisible.value?.let { isVisible ->
                    if ((dx < 0 || dy > 0) && isVisible) {
                        viewModel.setHomeSearchLayoutVisible(!isVisible)
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        sharedViewModel.setBottomNavigationVisible(true)
                        viewModel.setHomeSearchLayoutVisible(true)
                    }
                }
            }
        })
        binding.homeRecyclerview.addOnScrollListener(object :
            RecyclerViewScrollListener(RecyclerViewScrollListener.LoadMorePosition.TOP) {
            override fun onLoadMore() {
                viewModel.setMovieReqModel(false, true)
            }

            override fun onVisibleFirst() {
            }
        })
        viewModel.setSearchText()
    }

    private fun onObserve() {
        viewModel.movieList.observe(viewLifecycleOwner) {
            Log.e("homeFragment!!", it.toString())
            adapter.onDeleteAll()
            adapter.onItemsAdd(it)
        }
        viewModel.loadMovieList.observe(viewLifecycleOwner) {
            Log.e("loadmoreItem!!", it.toString())
            adapter.onItemsAdd(it)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
            }
    }
}