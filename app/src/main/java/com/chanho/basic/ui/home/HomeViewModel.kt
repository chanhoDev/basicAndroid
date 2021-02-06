package com.chanho.basic.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanho.basic.model.Movie
import com.chanho.basic.model.MovieReqModel
import com.chanho.basic.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers

class HomeViewModel
@ViewModelInject constructor(private val repository: Repository) : ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    private val _loadMovieList = MutableLiveData<List<Movie>>()
    val loadMovieList: LiveData<List<Movie>> = _loadMovieList

    private val _homeSearchLayoutVisible = MutableLiveData(true)
    val homeSearchLayoutVisible: LiveData<Boolean> = _homeSearchLayoutVisible

    val searchText = MutableLiveData<String>()

    private lateinit var reqMovieModel: MovieReqModel

    @SuppressLint("CheckResult")
    fun setSearchText() {
        repository.getSearchList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movieSearch ->
                searchText.value = movieSearch[0].search
            }, {
                Log.e("error", it.toString())
            })
    }

    fun setHomeSearchLayoutVisible(isVisible: Boolean) {
        _homeSearchLayoutVisible.value = isVisible
    }

    fun setMovieReqModel(isSearch: Boolean, isLoadModer: Boolean) {
        if (isLoadModer) {
            reqMovieModel.start =
                (reqMovieModel.start.toInt() + (reqMovieModel.display.toInt())).toString()
        } else if (isSearch) {
            reqMovieModel = MovieReqModel()
            reqMovieModel.query = searchText.value.toString()
        }
        getNaverMovieList(isLoadModer)
    }

    fun onSearchBtnClicked() {
        if (searchText.value.isNullOrEmpty()) {
            searchText.value = searchText.value?.trim()
        }
        setMovieReqModel(true, false)
    }

    @SuppressLint("CheckResult")
    fun getNaverMovieList(isLoadModer: Boolean) {
        Log.e("reqModel", reqMovieModel.toString())
        repository.getNaverMovieList(reqMovieModel)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.isSuccessful) {
//                    Log.e("successful", response.body()?.items.toString())
                    response.body()?.items?.let {
                        for (i in it) {
                            Log.e("movie = ", i.title.toString())
                        }
                        Log.e("==========", "======")
                    }
                    if (isLoadModer) {
                        _loadMovieList.value = response.body()?.items
                    } else {
                        _movieList.value = response.body()?.items
                    }
                } else {
                    Log.e("fail", response.message().toString())
                }
            }, { error ->
                Log.e("fail", error.toString())
            })
    }

}