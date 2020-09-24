package com.dodemy.legocatalogmvvmroompaging.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dodemy.legocatalogmvvmroompaging.legoset.data.LegoSetDao
import com.dodemy.legocatalogmvvmroompaging.util.getValue
import com.dodemy.legocatalogmvvmroompaging.util.legoThemeId
import com.dodemy.legocatalogmvvmroompaging.util.testLegoSetA
import com.dodemy.legocatalogmvvmroompaging.util.testLegoSetB
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LegoSetDaoTest : DbTest() {
    private lateinit var legoSetDao: LegoSetDao
    private val setA = testLegoSetA.copy()
    private val setB = testLegoSetB.copy()
    private val themeId = legoThemeId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before fun createDb() {
        legoSetDao = db.legoSetDao()

        // Insert legoSets in non-alphabetical order to test that results are sorted by name
        runBlocking {
            legoSetDao.insertAll(listOf(setA, setB))
        }
    }

    @Test fun testGetSets() {
        val list = getValue(legoSetDao.getLegoSets(themeId))
        assertThat(list.size, equalTo(2))

        // Ensure legoSet list is sorted by name
        assertThat(list[0], equalTo(setA))
        assertThat(list[1], equalTo(setB))
    }

    @Test fun testGetLegoSet() {
        assertThat(getValue(legoSetDao.getLegoSet(setA.id)), equalTo(setA))
    }
}