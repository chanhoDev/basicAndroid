package com.chanho.basic.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chanho.basic.R
import com.chanho.basic.databinding.ItemStoreBinding
import com.chanho.basic.model.Store

class MainAdapter constructor(
    val viewModel: MainViewModel
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var items = ArrayList<Store>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = DataBindingUtil.inflate<ItemStoreBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_store,
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.store(items[position])
    }

    fun deleteItems(){
        items.clear()
        notifyDataSetChanged()
    }
    fun setItems(storeList:List<Store>){
        this.items.addAll(storeList)
        notifyItemRangeChanged(items.size-1,storeList.size)
    }

    inner class MainViewHolder constructor(val binding: ItemStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun store(storeItem: Store) {
            binding.vm = viewModel
            binding.model = storeItem
        }
    }
}
