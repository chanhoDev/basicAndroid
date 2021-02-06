package com.chanho.basic.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanho.basic.repository.Repository

class MainViewModel
@ViewModelInject
constructor(private val repository: Repository) : ViewModel() {

    private val _bottomNavigationVisible = MutableLiveData(true)
    val bottomNavigationVisible :LiveData<Boolean> = _bottomNavigationVisible

    fun setBottomNavigationVisible(isVisible:Boolean){
        _bottomNavigationVisible.value = isVisible
    }

}