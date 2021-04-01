package com.example.swordhealthchallenge.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swordhealthchallenge.models.Breed

class DetailsViewModelFactory (private val breed: Breed) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Breed::class.java).newInstance(breed)
    }

}