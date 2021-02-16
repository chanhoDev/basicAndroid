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
import com.chanho.basic.room.MovieFavoriteEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel
@ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    private val compositDisposable = CompositeDisposable()

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    private val _loadMovieList = MutableLiveData<List<Movie>>()
    val loadMovieList: LiveData<List<Movie>> = _loadMovieList

    private val _homeSearchLayoutVisible = MutableLiveData(true)
    val homeSearchLayoutVisible: LiveData<Boolean> = _homeSearchLayoutVisible

    private val _onFilterClicked = MutableLiveData<Unit>()
    val onFilterClicked: LiveData<Unit> = _onFilterClicked

    val searchText = MutableLiveData<String>()

    private val _toastMsg = MutableLiveData<String>()
    val toastMsg: LiveData<String> = _toastMsg

    private lateinit var reqMovieModel: MovieReqModel

    fun clearCompositDisposable() {
        compositDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        compositDisposable.dispose()
    }

    @SuppressLint("CheckResult")
    fun setSearchText() {
        compositDisposable.add(
            repository.getSearchList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieSearch ->
                    Log.e("searchText", movieSearch.toString())
                    if (!movieSearch.isNullOrEmpty()) {
                        searchText.value = movieSearch[0].search
                    }
                }, {
                    Log.e("error", it.toString())
                })
        )
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
        compositDisposable.add(
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
        )

    }

    //영화 아이템 클릭시 이벤트
    @SuppressLint("CheckResult")
    fun onItemMovieClicked(movieItem: Movie) {
        compositDisposable.add(
            repository.isExistMovieFavorite(movieItem.title ?: "", movieItem.director ?: "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it > 0) {
                        _toastMsg.value = "즐겨찾기에 이미 추가되어있습니다."

                    } else {
                        onItemMovieFavoriteInsert(movieItem)
                    }
                }, {
                    Log.e("fail", it.toString())
                })
        )
    }

    //즐겨찾기 아이템 입력
    fun onItemMovieFavoriteInsert(movieItem: Movie) {
        compositDisposable.add(
            repository.setMovieFavorite(
                MovieFavoriteEntity(
                    title = movieItem.title ?: "",
                    link = movieItem.link ?: "",
                    image = movieItem.image ?: "",
                    subtitle = movieItem.subtitle ?: "",
                    pubDate = movieItem.pubDate ?: "",
                    director = movieItem.director ?: "",
                    actor = movieItem.actor ?: "",
                    userRating = movieItem.userRating ?: ""
                )
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _toastMsg.value = "즐겨찾기 추가됨"
                }, {
                    _toastMsg.value = "즐겨찾기 추가가 실패했습니다."
                })
        )
    }

    //필터 클릭시 이벤트
    fun onFilterClicked() {
        _onFilterClicked.value = Unit
    }

}