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
import com.chanho.basic.databinding.FragmentHomeListBinding
import com.chanho.basic.ui.detail.DetailFragment
import com.chanho.basic.ui.main.MainViewModel
import com.chanho.basic.util.RecyclerViewScrollListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeListBinding
    private val adapter: HomeAdapter by lazy {
        HomeAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(inflater.context),
            R.layout.fragment_home_list,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.vm = viewModel
        onObserve()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.run {
            clearCompositDisposable()
        }
    }

    override fun onStart() {
        super.onStart()
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        with(binding.homeRecyclerview) {
            layoutManager = linearLayoutManager
            this.adapter = this@HomeListFragment.adapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
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
            addOnScrollListener(object : RecyclerViewScrollListener() {
                override fun onLoadMore(currentPage: Int) {
                    viewModel.setMovieReqModel(currentPage)
                }


            })
        }

    }

    private fun onObserve() {
        viewModel.movieList.observe(viewLifecycleOwner) {
            Log.e("homeFragment!!", it.toString())
            adapter.onItemsAdd(it)
        }
        viewModel.loadMovieList.observe(viewLifecycleOwner) {
            Log.e("loadmoreItem!!", it.toString())
            adapter.onItemsAdd(it)
        }
        viewModel.toastMsg.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            sharedViewModel.getFavoriteMovieList()
        }
        viewModel.onSearchClicked.observe(viewLifecycleOwner) {
            adapter.onDeleteAll()
        }
        viewModel.onItemClicked.observe(viewLifecycleOwner) {
            parentFragmentManager.beginTransaction()
                .add(R.id.home_container, DetailFragment.newInstance(it), "detail")
                .addToBackStack("detail")
                .commit()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            HomeListFragment().apply {
            }
    }
}