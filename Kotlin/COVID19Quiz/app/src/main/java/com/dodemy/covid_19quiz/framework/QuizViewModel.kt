package com.dodemy.covid_19quiz.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dodemy.covid_19quiz.QuizApplication
import com.dodemy.covid_19quiz.domain.entities.BaseResponse
import com.dodemy.covid_19quiz.domain.entities.QuizData
//import com.dodemy.covid_19quiz.util.States
import com.dodemy.covid_19quiz.util.States
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuizViewModel(application: Application, protected val interators: Iterators): AndroidViewModel(application) {
    protected val application: QuizApplication = getApplication()
    private val _quizList = MutableLiveData<States<BaseResponse?>>()
    val quizList: LiveData<States<BaseResponse?>>
        get() = _quizList

    init {
        loadQuizFromRemote()
    }

    private fun loadQuizFromLocal() {
        GlobalScope.launch {
            _quizList.postValue(States.Success(message = "Success", data = interators.getQuizLocal().value))
        }
    }

    private fun insertQuizIntoLocal(quizData: BaseResponse?) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                interators.insertQuiz(quizData)
            }
        }
        loadQuizFromLocal()
    }

    private fun loadQuizFromRemote() {
        _quizList.postValue(States.Loading("Loading"))
        interators.getQuizRemote().enqueue(object : Callback<List<QuizData>?> {
            override fun onResponse(
                call: Call<List<QuizData>?>,
                response: Response<List<QuizData>?>
            ) {
                val baseResponse = BaseResponse(0, response.body())
                insertQuizIntoLocal(baseResponse)
            }

            override fun onFailure(call: Call<List<QuizData>?>, t: Throwable) {
                _quizList.postValue(States.Error("Something went wrong", t))
            }
        })
    }
}