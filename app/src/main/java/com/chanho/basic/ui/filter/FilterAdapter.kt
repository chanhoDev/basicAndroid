package com.chanho.basic.ui.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chanho.basic.R
import com.chanho.basic.databinding.ItemFilterGenreBinding
import com.chanho.basic.model.Genre

class FilterAdapter(var viewModel: FilterViewModel) :
    RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
    private var items = ArrayList<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        var binding: ItemFilterGenreBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_filter_genre, parent, false
        )
        return FilterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.onBindView(items[position])
    }

    fun deleteItemList() {
        items.clear()
        notifyItemRangeRemoved(0, items.size)
    }

    fun setItemList(genreList: ArrayList<Genre>) {
        items.addAll(genreList)
        notifyItemRangeInserted(items.size - 1, genreList.size)
    }

    inner class FilterViewHolder(var binding: ItemFilterGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBindView(model: Genre) {
            binding.vm = viewModel
            binding.model = model
        }
    }
}