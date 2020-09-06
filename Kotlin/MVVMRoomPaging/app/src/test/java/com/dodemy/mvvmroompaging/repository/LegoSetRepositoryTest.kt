package com.dodemy.mvvmroompaging.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dodemy.mvvmroompaging.api.LegoService
import com.dodemy.mvvmroompaging.data.AppDatabase
import com.dodemy.mvvmroompaging.legoset.data.LegoSetDao
import com.dodemy.mvvmroompaging.legoset.data.LegoSetRemoteDataSource
import com.dodemy.mvvmroompaging.legoset.data.LegoSetRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class LegoSetRepositoryTest {
    private lateinit var repository: LegoSetRepository
    private val dao = mock(LegoSetDao::class.java)
    private val service = mock(LegoService::class.java)
    private val remoteDataSource = LegoSetRemoteDataSource(service)
    private val mockRemoteDataSource = spy(remoteDataSource)

    private val themeId = 456
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Before
    fun init() {
        val db = mock(AppDatabase::class.java)
        `when`(db.legoSetDao()).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = LegoSetRepository(dao, remoteDataSource)
    }

    @Test
    fun loadLegoSetsFromNetwork() {
        runBlocking {
            repository.observePagedSets(connectivityAvailable = true,
                    themeId = themeId, coroutineScope = coroutineScope)

            verify(dao, never()).getLegoSets(themeId)
            verifyZeroInteractions(dao)
        }
    }

}