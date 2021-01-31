package com.chanho.basic.ui.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanho.basic.model.Movie
import com.chanho.basic.model.MovieReqModel
import com.chanho.basic.repository.Repository

class Main2ViewModel
@ViewModelInject
constructor(private val repository: Repository) : ViewModel() {
    init {
        getNaverMovieList()
    }
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList :LiveData<List<Movie>> = _movieList

//    lateinit var location:Location
//
//    private val _storeInfo = MutableLiveData<StoreInfo>()
//    val storeInfo :LiveData<StoreInfo> = _storeInfo
//
//    @SuppressLint("CheckResult")
//    fun fetchStoreInfo(isLocation:Boolean){
//        var lat:Double
//        var lon:Double
//        if(isLocation){
//            lat = location.latitude
//            lon = location.longitude
//        }else{
//            lat = 37.188078
//            lon = 127.043002
//        }
//        repository.getFetchStoreInfo(lat,lon)
//            .subscribe({response->
//                if(response.isSuccessful){
//                    _storeInfo.value=response.body()
//                    Log.e("successful",response.body()?.stores.toString())
//                }else{
//                    Log.e("fail",response.message().toString())
//                }
//            },{
//                Log.e("fail!!",it.toString())
//            })
//    }

    @SuppressLint("CheckResult")
    fun getNaverMovieList() {
        val reqModel = MovieReqModel()
        repository.getNaverMovieList(reqModel)
            .subscribe({ response ->
                if (response.isSuccessful) {
                    Log.e("successful", response.body()?.items.toString())
                    _movieList.value = response.body()?.items
                } else {
                    Log.e("fail", response.message().toString())
                }
            }, { error ->
                Log.e("fail", error.toString())
            })
    }
}