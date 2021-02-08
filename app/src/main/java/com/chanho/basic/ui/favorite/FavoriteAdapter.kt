package com.chanho.basic.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chanho.basic.R
import com.chanho.basic.databinding.ItemFavoriteMovieBinding
import com.chanho.basic.databinding.ItemMovieBinding
import com.chanho.basic.model.Movie

class FavoriteAdapter(val viewModel: FavoriteViewModel) :
    RecyclerView.Adapter<FavoriteAdapter.HomeViewHolder>() {
    private var items = ArrayList<Movie>()

    inner class HomeViewHolder(val binding: ItemFavoriteMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBindView(movie: Movie) {
            binding.vm = viewModel
            binding.item = movie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun onDeleteAll() {
        items.clear()
        notifyDataSetChanged()
    }

    fun onItemsAdd(movieList: List<Movie>) {
        items.addAll(movieList)
        notifyItemRangeInserted(items.size - 1, movieList.size)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBindView(items[position])
    }
}