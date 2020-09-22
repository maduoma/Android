package com.dodemy.cryptocoinmarket;

import com.dodemy.cryptocoinmarket.retrofit.info.Info;
import com.dodemy.cryptocoinmarket.retrofit.listings.CryptoList;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface APIInterface {

    //https://pro.coinmarketcap.com/api/v1#section/Quick-Start-Guide
    //@Headers("X-CMC_PRO_API_KEY: XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX")

    @GET("/v1/cryptocurrency/listings/latest?")
    Single<CryptoList> getMarketPairsLatest(@Query("limit") String limit);

    @GET("/v1/cryptocurrency/info")
    Single<Info> getCryptocurrencyInfo(@Query("symbol") String symbol);

}
