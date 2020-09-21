package com.dodemy.cryptocurrency.helpers

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.withProgress(
    mutableLiveData: MutableLiveData<Boolean>
): Observable<T> {

    return compose {
        it.doOnSubscribe { _ ->
            mutableLiveData.postValue(true)
        }.doAfterTerminate {
            mutableLiveData.postValue(false)
        }
    }
}

fun <T> Observable<T>.showErrorMessages(
    mutableLiveData: MutableLiveData<ErrorMessage>
): Observable<T> {

    return compose {
        it.doOnError { error ->

            mutableLiveData.postValue(
                ErrorMessage(error.localizedMessage)
            )
        }
    }
}

fun <T> Observable<T>.observeOnMainThread(): Observable<T> =
    observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.subscribeOnIOThread(): Observable<T> =
    subscribeOn(Schedulers.io())