package com.dodemy.cryptocurrency.mainView.model

import com.google.gson.annotations.SerializedName

data class CoinModel(
    @SerializedName("24h_volume_usd")
    val hVolumeUsd: String = "",
    @SerializedName("available_supply")
    val availableSupply: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("last_updated")
    val lastUpdated: String = "",
    @SerializedName("market_cap_usd")
    val marketCapUsd: String = "",
    @SerializedName("max_supply")
    val maxSupply: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("percent_change_1h")
    val percentChange1h: String = "",
    @SerializedName("percent_change_24h")
    val percentChange24h: String = "",
    @SerializedName("percent_change_7d")
    val percentChange7d: String = "",
    @SerializedName("price_btc")
    val priceBtc: String = "",
    @SerializedName("price_usd")
    val priceUsd: String = "",
    @SerializedName("rank")
    val rank: String = "",
    @SerializedName("symbol")
    val symbol: String = "",
    @SerializedName("total_supply")
    val totalSupply: String = ""
)