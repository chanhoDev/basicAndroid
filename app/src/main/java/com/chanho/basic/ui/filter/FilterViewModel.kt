package com.chanho.basic.ui.filter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanho.basic.model.Country
import com.chanho.basic.model.Genre
import com.chanho.basic.repository.Repository

class FilterViewModel
@ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    private val _genreItemClicked = MutableLiveData<Int>()
    val genreItemClicked: LiveData<Int> = _genreItemClicked

    private val _countryItemClicked = MutableLiveData<Int>()
    val countryItemClicked: LiveData<Int> = _countryItemClicked

    private val _genreItemList = MutableLiveData<ArrayList<Genre>>()
    val genreItemList: LiveData<ArrayList<Genre>> = _genreItemList

    private val _countryItemList = MutableLiveData<ArrayList<Country>>()
    val countryItemList: LiveData<ArrayList<Country>> = _countryItemList

    fun setGenreItemList(genreTextList: Array<String>) {
        val genreList = ArrayList<Genre>()
        for (id in genreTextList.indices) {
            genreList.add(Genre(id, genreTextList[id], false))
        }
        _genreItemList.value = genreList
    }
    fun setCountryList(
        countryKeyList:Array<String>,
        countryValueList: Array<String>
    ){
        val countryList = ArrayList<Country>()
        for(i in countryKeyList.indices){
            countryList.add(Country(countryKeyList[i],countryValueList[i],false))
        }
        _countryItemList.value = countryList
    }

    //장르 아이템 클릭시 이벤트
    fun onGenreItemClicked(itemGenre: Genre,position:Int) {
        _genreItemList.value?.let { items->
            for(i in items.indices){
                if(i==position){
                    itemGenre.check = !itemGenre.check
                    _genreItemClicked.value = position
                }else{
                    if(items[i].check){
                        items[i].check=!items[i].check
                        _genreItemClicked.value = i
                    }
                }
            }
        }
    }

    //장르 아이템 클릭시 이벤트
    fun onCountryItemClicked(itemCountry: Country,position:Int) {
        _countryItemList.value?.let { items->
            for(i in items.indices){
                if(i==position){
                    itemCountry.check = !itemCountry.check
                    _countryItemClicked.value = position
                }else{
                    if(items[i].check){
                        items[i].check=!items[i].check
                        _countryItemClicked.value = i
                    }
                }
            }
        }
    }
}