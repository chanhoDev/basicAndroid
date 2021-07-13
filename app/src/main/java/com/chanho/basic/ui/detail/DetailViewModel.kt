package com.chanho.basic.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanho.basic.model.Movie
import com.chanho.basic.model.MovieReqModel
import com.chanho.basic.repository.Repository

class DetailViewModel
@ViewModelInject constructor(private val repository: Repository) : ViewModel() {
    private val _onBackClickEvent = MutableLiveData<Unit>()
    val onBackClickEvent: LiveData<Unit> = _onBackClickEvent

    fun onBackClicked(){
        _onBackClickEvent.value = Unit
    }

}