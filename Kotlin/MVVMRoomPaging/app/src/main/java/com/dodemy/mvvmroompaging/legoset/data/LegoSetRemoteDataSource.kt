package com.dodemy.mvvmroompaging.legoset.data

import com.dodemy.mvvmroompaging.api.BaseDataSource
import com.dodemy.mvvmroompaging.api.LegoService
import com.dodemy.mvvmroompaging.testing.OpenForTesting
import javax.inject.Inject

/**
 * Works with the Lego API to get data.
 */
@OpenForTesting
class LegoSetRemoteDataSource @Inject constructor(private val service: LegoService) : BaseDataSource() {

    suspend fun fetchSets(page: Int, pageSize: Int? = null, themeId: Int? = null)
            = getResult { service.getSets(page, pageSize, themeId, "-year") }

    suspend fun fetchSet(id: String)
            = getResult { service.getSet(id) }
}
