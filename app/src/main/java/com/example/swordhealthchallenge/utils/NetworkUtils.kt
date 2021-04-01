package com.example.swordhealthchallenge.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {


    // Verify connection to the network/internet
    fun isConnected(context: Context): Boolean {

        // Get connectivity manager
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Get Network info
        val networkInfo = connectivityManager.activeNetworkInfo

        // Return if or not network is available and internet is connected
        return networkInfo != null && networkInfo.isConnected
    }

}