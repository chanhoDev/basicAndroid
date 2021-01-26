package com.chanho.basic.ui

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanho.basic.model.StoreInfo
import com.chanho.basic.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel
@ViewModelInject
    constructor(private val repository: Repository):ViewModel() {

    lateinit var location:Location

    private val _storeInfo = MutableLiveData<StoreInfo>()
    val storeInfo :LiveData<StoreInfo> = _storeInfo

    @SuppressLint("CheckResult")
    fun fetchStoreInfo(){
        repository.getFetchStoreInfo(location.latitude,location.longitude)
            .subscribe({response->
                if(response.isSuccessful){
                    _storeInfo.value=response.body()
                    Log.e("successful",response.body()?.stores.toString())
                }else{
                    Log.e("fail",response.message().toString())
                }
            },{
                Log.e("fail!!",it.toString())
            })
    }
}