package com.chanho.basic.ui.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanho.basic.model.Movie
import com.chanho.basic.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers

class MainViewModel
@ViewModelInject
constructor(private val repository: Repository) : ViewModel() {

    private val _bottomNavigationVisible = MutableLiveData(true)
    val bottomNavigationVisible: LiveData<Boolean> = _bottomNavigationVisible

    private val _filterClicked = MutableLiveData<Unit>()
    val filterClicked: LiveData<Unit> = _filterClicked

    fun onFilterClicked() {
        _filterClicked.value = Unit
    }

    fun setBottomNavigationVisible(isVisible: Boolean) {
        _bottomNavigationVisible.value = isVisible
    }

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    @SuppressLint("CheckResult")
    fun getFavoriteMovieList() {
        repository.getMovieFavoriteList()
            .observeOn(AndroidSchedulers.mainThread())
            .map { movieFavoriteList ->
                var movieList = ArrayList<Movie>()
                if (movieFavoriteList.size > 0) {
                    for (item in movieFavoriteList) {
                        movieList.add(
                            Movie(
                                item.id,
                                item.title,
                                item.link,
                                item.image,
                                item.subtitle,
                                item.pubDate,
                                item.director,
                                item.actor,
                                item.userRating
                            )
                        )
                    }
                }
                movieList
            }
            .subscribe({ movieList ->
                if (movieList.size > 0) {
                    _movieList.value = movieList
                }
            }, {
                Log.e("favoriteError",it.message.toString())
            })
    }

}