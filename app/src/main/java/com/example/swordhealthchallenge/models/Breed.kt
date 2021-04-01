package com.example.swordhealthchallenge.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Breed(@SerializedName("name") val name: String,
                 @SerializedName("breed_group") val group: String,
                 @SerializedName("origin") val origin: String,
                 @SerializedName("temperament") val temperament: String,
                 @SerializedName("image") val image: Image?) : Serializable
