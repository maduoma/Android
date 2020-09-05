package com.dodemy.gadsleaderboardaad.ui.learningLeaders

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dodemy.gadsleaderboardaad.network.repository.ApiRepository
import com.dodemy.gadsleaderboardaad.model.Hours
import com.dodemy.gadsleaderboardaad.network.LoadingStatus
import com.dodemy.gadsleaderboardaad.network.Result
import kotlinx.coroutines.launch

class LearningLeadersViewModel @ViewModelInject constructor(private val repository: ApiRepository) :
    ViewModel() {

    private val leaders = MutableLiveData<List<Hours>>()
    val _leaders: LiveData<List<Hours>>
        get() = leaders

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    init {
        getHoursLeader()
    }

    private fun getHoursLeader() {
        _loadingStatus.value = LoadingStatus.Loading("Fetching Leaders")
        viewModelScope.launch {
            when (val result = repository.getHours()) {
                is Result.Success -> {
                    leaders.value = result.data
                    _loadingStatus.value = LoadingStatus.Success
                }
                is Result.Error -> _loadingStatus.value = LoadingStatus.Error(result.message)
            }
        }
    }

}