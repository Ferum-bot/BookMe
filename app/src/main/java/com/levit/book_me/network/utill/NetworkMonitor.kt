package com.levit.book_me.network.utill

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.levit.book_me.core_network.model.enums.NetworkStatus
import kotlinx.coroutines.runBlocking

object NetworkMonitor {

    var isNetworkAvailable: Boolean = true
    private set

    private val networkCallback = object: ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            isNetworkAvailable = true
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            isNetworkAvailable = false
        }

    }

    fun startListening(application: Application) {
        val manager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder()

        manager.registerNetworkCallback(
            builder.build(),
            networkCallback,
        )
    }


    fun stopListening(application: Application) {
        val manager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        manager.unregisterNetworkCallback(networkCallback)
    }
}