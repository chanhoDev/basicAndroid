package com.chanho.basic.ui.filter

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.chanho.basic.R
import com.chanho.basic.databinding.ActivityFilterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterActivity : AppCompatActivity() {
    private val viewModel: FilterViewModel by viewModels()
    private lateinit var binding: ActivityFilterBinding
    private val owner = this@FilterActivity
    private val adapter: FilterAdapter by lazy {
        FilterAdapter(viewModel)
    }
    private val countryAdapter: FilterCountryAdapter by lazy {
        FilterCountryAdapter(viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(owner, R.layout.activity_filter)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        viewModel.setGenreItemList(resources.getStringArray(R.array.genre_list))
        binding.filterGenreRecyclerview.layoutManager = GridLayoutManager(owner, 3)
        binding.filterGenreRecyclerview.adapter = adapter
        binding.filterGenreRecyclerview.overScrollMode = View.OVER_SCROLL_NEVER

        viewModel.setCountryList(
            resources.getStringArray(R.array.country_key_list),
            resources.getStringArray(R.array.country_value_list)
        )
        binding.filterCountryRecyclerview.layoutManager = GridLayoutManager(owner, 3)
        binding.filterCountryRecyclerview.adapter = countryAdapter
        binding.filterCountryRecyclerview.animation=null
        binding.filterCountryRecyclerview.overScrollMode = View.OVER_SCROLL_NEVER


        onObserve()
    }


    private fun onObserve() {
        viewModel.genreItemList.observe(owner) { genreList ->
            adapter.deleteItemList()
            adapter.setItemList(genreList)
        }
        viewModel.genreItemClicked.observe(owner) { position ->
            adapter.setNotify(position)
        }

        viewModel.countryItemList.observe(owner) { countryList ->
            countryAdapter.deleteItemList()
            countryAdapter.setItemList(countryList)
        }
        viewModel.countryItemClicked.observe(owner) { position ->
            countryAdapter.setNotify(position)
        }
    }

}
