package com.example.swordhealthchallenge.viewModels

import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.BuildConfig
import com.example.swordhealthchallenge.models.Breed
import com.example.swordhealthchallenge.network.NetworkInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel : ViewModel() {

    private val isInit: MutableLiveData<Boolean> = MutableLiveData()

    private val receivedList: MutableLiveData<List<Breed>> = MutableLiveData()
    private val receivedToast: MutableLiveData<String> = MutableLiveData()

    init{
        isInit.postValue(true)
    }

    fun isInit(): MutableLiveData<Boolean> {
        return isInit
    }

    fun getToast(): LiveData<String> {
        return receivedToast
    }

    fun getList(): LiveData<List<Breed>> {
        return receivedList
    }

    fun fetchBreedsByName(client: NetworkInterface, query: String){

        client
            .fetchBreedsByName(query,"asc", BuildConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                receivedList.postValue(it)
            }, {
                receivedToast.postValue("Error loading list!")
            })

    }

}