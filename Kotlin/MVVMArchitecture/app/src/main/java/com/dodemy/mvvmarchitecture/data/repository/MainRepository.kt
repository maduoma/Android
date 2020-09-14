package com.dodemy.mvvmarchitecture.data.repository

import com.dodemy.mvvmarchitecture.data.api.ApiHelper
import com.dodemy.mvvmarchitecture.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }

}