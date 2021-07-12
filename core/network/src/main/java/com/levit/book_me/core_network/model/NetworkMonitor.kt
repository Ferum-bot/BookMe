package com.levit.book_me.core_network.model

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object NetworkMonitor {

    private val _isNetworkAvailable: MutableLiveData<Boolean> = MutableLiveData()
    val isNetworkAvailable: LiveData<Boolean> = _isNetworkAvailable

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    fun setNetworkMonitor(application: Application) {
        val manager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder()

        manager.registerNetworkCallback(
                builder.build(),
                object: ConnectivityManager.NetworkCallback() {

                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        _isNetworkAvailable.postValue(true)
                    }

                    override fun onLost(network: Network) {
                        super.onLost(network)
                        _isNetworkAvailable.postValue(false)
                    }
                }
        )
    }

    fun removeNetworkMonitor(application: Application) {
        val manager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        manager.unregisterNetworkCallback(ConnectivityManager.NetworkCallback())
    }
}