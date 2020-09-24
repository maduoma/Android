package com.dodemy.legocatalogmvvmroompaging.legotheme.data

import com.dodemy.legocatalogmvvmroompaging.api.BaseDataSource
import com.dodemy.legocatalogmvvmroompaging.api.LegoService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class LegoThemeRemoteDataSource @Inject constructor(private val service: LegoService) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getThemes(1, 1000, "-id") }

}
