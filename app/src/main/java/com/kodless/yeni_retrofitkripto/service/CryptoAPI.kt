package com.kodless.yeni_retrofitkripto.service

import com.kodless.yeni_retrofitkripto.model.CryptoModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    @GET(value = "prices?key=<YOUR_KEY>")
    fun getData():Observable<List<CryptoModel>>

}