package com.dodemy.legocatalogmvvmroompaging.legoset.ui

import androidx.lifecycle.ViewModel
import com.dodemy.legocatalogmvvmroompaging.di.CoroutineScropeIO
import com.dodemy.legocatalogmvvmroompaging.legoset.data.LegoSetRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

/**
 * The ViewModel for [LegoSetsFragment].
 */
class LegoSetsViewModel @Inject constructor(private val repository: LegoSetRepository,
                                            @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope)
    : ViewModel() {

    var connectivityAvailable: Boolean = false
    var themeId: Int? = null

    val legoSets by lazy {
        repository.observePagedSets(
                connectivityAvailable, themeId, ioCoroutineScope)
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}
