package com.example.swordhealthchallenge.viewModels

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.models.Breed
import com.squareup.picasso.Picasso

class ItemBreedViewModel(breed: Breed) : ViewModel() {

    private val name: MutableLiveData<String> = MutableLiveData()
    private val image: MutableLiveData<String> = MutableLiveData()
    private val group: MutableLiveData<String> = MutableLiveData()
    private val origin: MutableLiveData<String> = MutableLiveData()

    private val goToDetails: MutableLiveData<Boolean> = MutableLiveData(false)

    init{
        name.postValue(breed.name)
        if(breed.image != null) {
            image.postValue(breed.image.url)
        }
        group.postValue(breed.group)
        origin.postValue(breed.origin)
    }

    fun getName(): LiveData<String> {
        return name
    }

    fun getImage(): LiveData<String> {
        return image
    }

    fun getGroup(): LiveData<String> {
        return group
    }

    fun getOrigin(): LiveData<String> {
        return origin
    }

    fun getGoToDetails(): LiveData<Boolean>{
        return goToDetails
    }

    fun onItemClick(view: View) {
        goToDetails.postValue(true)
    }

    object DataBindingAdapter {

        @BindingAdapter("app:productImage")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            // Use Picasso to load avatar from url
            Picasso.get()
                .load(imageUrl)
                .into(view)
        }

    }


}