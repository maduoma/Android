package com.dodemy.mvvm_creaturemon.viewmodel

import androidx.lifecycle.ViewModel
import com.dodemy.mvvm_creaturemon.model.CreatureRepository
import com.dodemy.mvvm_creaturemon.model.room.RoomRepository

class AllCreaturesViewModel(private val repository: CreatureRepository = RoomRepository()) :
        ViewModel() {

    private val allCreaturesLiveData = repository.getAllCreatures()

    fun getAllCreaturesLiveData() = allCreaturesLiveData

    fun clearAllCreatures() = repository.clearAllCreatures()
}