package com.example.swordhealthchallenge.network

import com.example.swordhealthchallenge.models.Breed
import com.example.swordhealthchallenge.utils.Constants.BREEDS_PATH
import com.example.swordhealthchallenge.utils.Constants.SEARCH_PATH
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInterface {


    @GET(BREEDS_PATH)
    fun fetchBreeds(
        @Query("limit") limit: String,
        @Query("page") page: String,
        @Query("order") order: String,
        @Query("api_key") key: String
    ): Observable<List<Breed>>

    @GET(SEARCH_PATH)
    fun fetchBreedsByName(
        @Query("q") query: String,
        @Query("order") order: String,
        @Query("api_key") key: String
    ): Observable<List<Breed>>

//    @GET(BREEDS_SEARCH_PATH)
//    fun fetchBreedsByName(
//        @Query("q") query: String,
//        @Query("api_key") key: String
//    ): Observable<Any>

}