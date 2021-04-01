package com.example.swordhealthchallenge.viewModels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.BuildConfig
import com.example.swordhealthchallenge.models.Breed
import com.example.swordhealthchallenge.network.NetworkInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val isInit: MutableLiveData<Boolean> = MutableLiveData()
    private val receivedList: MutableLiveData<List<Breed>> = MutableLiveData()
    private val receivedToast: MutableLiveData<String> = MutableLiveData()

    private val isListMode: MutableLiveData<Boolean> = MutableLiveData()
    private val isGridMode: MutableLiveData<Boolean> = MutableLiveData()

    private var currentPage : Int = 0

    init{
        isInit.postValue(true)
    }

    fun isInit(): MutableLiveData<Boolean> {
        return isInit
    }

    fun getToast(): LiveData<String> {
        return receivedToast
    }

    fun getLastPage(): Int{
        return currentPage
    }

    fun getList(): LiveData<List<Breed>> {
        return receivedList
    }

    fun isListMode(): MutableLiveData<Boolean> {
        return isListMode
    }

    fun isGridMode(): MutableLiveData<Boolean> {
        return isGridMode
    }

    fun onListModeClick(view: View) {
        isListMode.postValue(true)
    }

    fun onGridModeClick(view: View) {
        isGridMode.postValue(true)
    }

    fun fetchBreeds(client: NetworkInterface, page: String){

        currentPage = page.toInt()

        client
            .fetchBreeds("10", page, "asc", BuildConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                receivedList.postValue(it)
            }, {
                receivedToast.postValue("Error loading list!")
            })

    }


}