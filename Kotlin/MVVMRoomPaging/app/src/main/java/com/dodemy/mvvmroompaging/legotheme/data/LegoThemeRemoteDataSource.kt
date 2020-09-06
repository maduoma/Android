package com.dodemy.mvvmroompaging.legotheme.data

import com.dodemy.mvvmroompaging.api.BaseDataSource
import com.dodemy.mvvmroompaging.api.LegoService
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
class LegoThemeRemoteDataSource @Inject constructor(private val service: LegoService) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getThemes(1, 1000, "-id") }

}
