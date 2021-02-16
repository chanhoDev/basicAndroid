package com.chanho.basic.ui.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chanho.basic.R
import com.chanho.basic.databinding.ItemFilterCountryBinding
import com.chanho.basic.databinding.ItemFilterGenreBinding
import com.chanho.basic.model.Country
import com.chanho.basic.model.Genre

class FilterCountryAdapter(var viewModel: FilterViewModel) :
    RecyclerView.Adapter<FilterCountryAdapter.FilterViewHolder>() {
    private var items = ArrayList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_filter_country, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.onBindView(items[position])
    }
    fun setNotify(position:Int){
        notifyItemChanged(position)
    }

    fun deleteItemList() {
        items.clear()
        notifyItemRangeRemoved(0, items.size)
    }

    fun setItemList(genreList: ArrayList<Country>) {
        items.addAll(genreList)
        notifyItemRangeInserted(items.size - 1, genreList.size)
    }

    inner class FilterViewHolder(var binding: ItemFilterCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBindView(model: Country) {
            binding.vm = viewModel
            binding.model = model
            binding.pos = adapterPosition
        }
    }
}