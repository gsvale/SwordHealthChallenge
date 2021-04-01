package com.example.swordhealthchallenge.viewModels

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swordhealthchallenge.models.Breed
import com.squareup.picasso.Picasso

class DetailsViewModel(breed: Breed) : ViewModel() {

    private val name: MutableLiveData<String> = MutableLiveData()
    private val image: MutableLiveData<String> = MutableLiveData()
    private val group: MutableLiveData<String> = MutableLiveData()
    private val origin: MutableLiveData<String> = MutableLiveData()
    private val temperament: MutableLiveData<String> = MutableLiveData()



    init{
        name.postValue(breed.name)
        if(breed.image != null) {
            image.postValue(breed.image.url)
        }
        group.postValue(breed.group)
        origin.postValue(breed.origin)
        temperament.postValue(breed.temperament)
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

    fun getTemperament(): LiveData<String> {
        return temperament
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