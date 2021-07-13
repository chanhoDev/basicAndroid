package com.chanho.basic.ui.favorite

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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chanho.basic.R
import com.chanho.basic.databinding.FragmentFavoriteBinding
import com.chanho.basic.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val viewModel: FavoriteViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentFavoriteBinding
    private val adapter: FavoriteAdapter by lazy {
        FavoriteAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(inflater.context),
            R.layout.fragment_favorite,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.vm = viewModel
        sharedViewModel.getFavoriteMovieList()
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
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
//                Remove swiped item from list and notify the RecyclerView
                adapter.onDeleteItem(viewHolder.layoutPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.homeRecyclerview)
    }

    private fun onObserve() {
        sharedViewModel.movieList.observe(viewLifecycleOwner) {
            Log.e("homeFragment!!", it.toString())
            adapter.onDeleteAll()
            adapter.onItemsAdd(it)
        }
//        sharedViewModel.loadMovieList.observe(viewLifecycleOwner) {
//            Log.e("loadmoreItem!!", it.toString())
//            adapter.onItemsAdd(it)
//        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            FavoriteFragment().apply {
            }
    }
}