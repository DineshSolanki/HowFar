package com.aprogrammer.howfar

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class App : Application() {
    var apiService: Distance24? = null
        get() {
            if (field == null) {
                field = provideRetrofit("https://www.distance24.org/").create(Distance24::class.java)
            }
            return field
        }
        private set
    private var mInternetConnectionListener: InternetConnectionListener? = null
    override fun onCreate() {
        super.onCreate()
    }

    fun setInternetConnectionListener(listener: InternetConnectionListener?) {
        mInternetConnectionListener = listener
    }

    fun removeInternetConnectionListener() {
        mInternetConnectionListener = null
    }

    private val isInternetAvailable: Boolean
        get() {
            val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            //Aware of depreciation
            val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    private fun provideRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val okhttpClientBuilder = OkHttpClient.Builder()
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS)
        okhttpClientBuilder.addInterceptor(object : NetworkConnectionInterceptor() {
            override val isInternetAvailable: Boolean
                get() = this@App.isInternetAvailable

            override fun onInternetUnavailable() {
                if (mInternetConnectionListener != null) {
                    mInternetConnectionListener!!.onInternetUnavailable()
                }
            }
        })
        return okhttpClientBuilder.build()
    }
}