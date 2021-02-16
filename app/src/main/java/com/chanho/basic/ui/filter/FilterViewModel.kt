package com.chanho.basic.ui.filter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanho.basic.model.Genre
import com.chanho.basic.repository.Repository

class FilterViewModel
@ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    private val _genreItemClicked = MutableLiveData<Genre>()
    val genreItemClicked: LiveData<Genre> = _genreItemClicked

    private val _genreItemList = MutableLiveData<ArrayList<Genre>>()
    val genreItemList: LiveData<ArrayList<Genre>> = _genreItemList

    fun genreItemListCall(isFirst: Boolean) {

    }

    fun setGenreItemList(genreTextList: Array<String>) {
        var genreList = ArrayList<Genre>()
        for (id in genreTextList.indices) {
            genreList.add(Genre(id, genreTextList[id], false))
        }
        _genreItemList.value = genreList
    }

    //장르 아이템 클릭시 이벤트
    fun onGenreItemClicked(itemGenre: Genre) {
        itemGenre.check = !itemGenre.check
    }
}