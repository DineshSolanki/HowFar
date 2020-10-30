package com.aprogrammer.howfar

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
//https://stackoverflow.com/a/40667193/8076598
class ConnectivityInterceptor(isNetworkActive: Observable<Boolean?>) :
    Interceptor {
    private var isNetworkActive = false
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isNetworkActive) {
            throw NoConnectivityException()
        } else {
            chain.proceed(chain.request())
        }
    }

    init {
        isNetworkActive.subscribe(
            { _isNetworkActive -> this.isNetworkActive = _isNetworkActive!! }
        ) { _error -> Log.e("NetworkActive error ", _error.message!!) }
    }
}

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No network available, please check your WiFi or Data connection"
}