package com.chanho.basic.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.chanho.basic.R
import com.chanho.basic.databinding.FragmentFilterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFragment : Fragment() {
    private val viewModel: FilterViewModel by viewModels()
    private lateinit var binding: FragmentFilterBinding
    private val adapter: FilterAdapter by lazy {
        FilterAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(inflater.context),
            R.layout.fragment_filter,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.vm = viewModel
        viewModel.setGenreItemList(resources.getStringArray(R.array.genre_list))
        onObserve()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val gridLayoutManager = GridLayoutManager(context, 3)
        binding.filterGenreRecyclerview.layoutManager = gridLayoutManager
        binding.filterGenreRecyclerview.adapter = adapter
    }

    private fun onObserve() {
        viewModel.genreItemList.observe(requireActivity()) { genreList ->
            adapter.deleteItemList()
            adapter.setItemList(genreList)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FilterFragment().apply {
            }
    }
}
