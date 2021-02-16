package com.chanho.basic.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanho.basic.model.Movie
import com.chanho.basic.model.MovieReqModel
import com.chanho.basic.repository.Repository

class FavoriteViewModel
@ViewModelInject constructor(private val repository: Repository) : ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    private val _loadMovieList = MutableLiveData<List<Movie>>()
    val loadMovieList: LiveData<List<Movie>> = _loadMovieList

    private val _homeSearchLayoutVisible = MutableLiveData(true)
    val homeSearchLayoutVisible: LiveData<Boolean> = _homeSearchLayoutVisible

    val searchText = MutableLiveData<String>()

    private lateinit var reqMovieModel: MovieReqModel

    fun removeFavoriteItem(movie: Movie) {
        repository.deleteMovieFavoriteCall(movie.id ?: 0)
    }

    fun setHomeSearchLayoutVisible(isVisible: Boolean) {
        _homeSearchLayoutVisible.value = isVisible
    }

}