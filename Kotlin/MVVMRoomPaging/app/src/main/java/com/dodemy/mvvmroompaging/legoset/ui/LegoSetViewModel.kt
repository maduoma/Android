

package com.dodemy.mvvmroompaging.legoset.ui

import androidx.lifecycle.ViewModel
import com.dodemy.mvvmroompaging.legoset.data.LegoSetRepository
import javax.inject.Inject

/**
 * The ViewModel used in [LegoSetFragment].
 */
class LegoSetViewModel @Inject constructor(repository: LegoSetRepository) : ViewModel() {

    lateinit var id: String

    val legoSet by lazy { repository.observeSet(id) }

}
