package com.example.swordhealthchallenge.viewModels



import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class MainViewModel : ViewModel() {

    private val isInit: MutableLiveData<Boolean> = MutableLiveData()

    init{
        isInit.postValue(true)
    }

    fun isInit(): MutableLiveData<Boolean> {
        return isInit
    }

}