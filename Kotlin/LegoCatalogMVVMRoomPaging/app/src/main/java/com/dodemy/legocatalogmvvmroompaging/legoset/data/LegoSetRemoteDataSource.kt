package com.dodemy.legocatalogmvvmroompaging.legoset.data

import com.dodemy.legocatalogmvvmroompaging.api.BaseDataSource
import com.dodemy.legocatalogmvvmroompaging.api.LegoService
import com.dodemy.legocatalogmvvmroompaging.testing.OpenForTesting
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
