package com.dodemy.koingettingstarted.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dodemy.koingettingstarted.data.model.ImagesResponseModel
import com.dodemy.koingettingstarted.data.repository.ImagesRepo


/**
 *
 *
 * Usage:
 *
 * How to call:
 *
 * Useful parameter:
 *
 */

class MainActivityViewModel(private val imagesRepo: ImagesRepo) : ViewModel() {
    var imageResponseLiveData = MutableLiveData<ImagesResponseModel>()

    fun getImages() {
        imagesRepo.getImages(imageResponseLiveData)
    }
}