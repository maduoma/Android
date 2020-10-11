package com.dodemy.githubtrendingcontents.view.ui.repolist

import androidx.lifecycle.MutableLiveData
import com.dodemy.githubtrendingcontents.model.Item

import com.dodemy.githubtrendingcontents.model.RepoRepository
import com.dodemy.githubtrendingcontents.view.base.BaseViewModel

class RepoListViewModel : BaseViewModel() {
    val repoListLive = MutableLiveData<List<Item>>()

    fun fetchRepoList() {
        dataLoading.value = true
        RepoRepository.getInstance().getRepoList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                repoListLive.value = response?.items
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}