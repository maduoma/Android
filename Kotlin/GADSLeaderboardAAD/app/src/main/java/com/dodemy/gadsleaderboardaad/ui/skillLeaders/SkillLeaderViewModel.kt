package com.dodemy.gadsleaderboardaad.ui.skillLeaders

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dodemy.gadsleaderboardaad.model.Skills
import com.dodemy.gadsleaderboardaad.network.LoadingStatus
import com.dodemy.gadsleaderboardaad.network.repository.ApiRepository
import com.dodemy.gadsleaderboardaad.network.Result
import kotlinx.coroutines.launch

class SkillLeaderViewModel @ViewModelInject constructor(private val repository: ApiRepository) :
    ViewModel() {

    private val _skillLeaders = MutableLiveData<List<Skills>>()
    val skillLeaders: LiveData<List<Skills>>
        get() = _skillLeaders

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    init {
        getSkillLeaders()
    }

    private fun getSkillLeaders() {
        _loadingStatus.value = LoadingStatus.Loading("Fetching Skill Leaders ...")
        viewModelScope.launch {
            when (val result = repository.getSkills()) {
                is Result.Success -> {
                    _loadingStatus.value = LoadingStatus.Success
                    _skillLeaders.value = result.data
                }
                is Result.Error -> {
                    _loadingStatus.value = LoadingStatus.Error(result.message)
                }
            }
        }
    }

}