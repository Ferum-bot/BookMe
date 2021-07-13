package com.levit.book_me.core_network.model

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.levit.book_me.core_network.model.enums.NetworkStatus
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.runBlocking

class NetworkMonitor(
    private val application: Application
) {

    companion object {

        private const val REPLAY_COUNT = 0
        private const val EXTRA_CAPACITY = 0
    }

    private val _isNetworkAvailable: MutableSharedFlow<NetworkStatus> = MutableSharedFlow(
        replay = REPLAY_COUNT,
        extraBufferCapacity = EXTRA_CAPACITY,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val isNetworkAvailable: SharedFlow<NetworkStatus> = _isNetworkAvailable

    private val networkCallback = object: ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)

            runBlocking {
                _isNetworkAvailable.emit(NetworkStatus.NETWORK_AVAILABLE)
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)

            runBlocking {
                _isNetworkAvailable.emit(NetworkStatus.NETWORK_LOST)
            }
        }

    }

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    fun startMonitoringNetwork() {
        val manager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder()

        manager.registerNetworkCallback(
            builder.build(),
            networkCallback,
        )
    }

    fun stopMonitoringNetwork() {
        val manager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        manager.unregisterNetworkCallback(networkCallback)
    }
}
