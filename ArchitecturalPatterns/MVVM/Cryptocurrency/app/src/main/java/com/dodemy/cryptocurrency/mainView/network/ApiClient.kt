package com.dodemy.cryptocurrency.mainView.network

import com.dodemy.cryptocurrency.mainView.model.CoinModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiClient {

    @GET("v1/ticker/")
    fun getAllCoins(): Observable<List<CoinModel>>
}