package com.aprogrammer.howfar

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface Distance24 {
    @GET("route.json?")
    fun getData(
        @Query("stops") fromTo: String?
    ): Observable<PlacesData>
}