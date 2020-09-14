package com.dodemy.mvvmarchitecture.data.api

import com.dodemy.mvvmarchitecture.data.model.User
import io.reactivex.Single

interface ApiService {

    fun getUsers(): Single<List<User>>

}