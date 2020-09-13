package com.dodemy.gadsproject_aad


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dodemy.gadsproject_aad.datasource.APIClient
import com.dodemy.gadsproject_aad.model.SkillIQ
import com.dodemy.gadsproject_aad.model.TopLearner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataViewModel : ViewModel() {
    var skillMutableData = MutableLiveData<List<SkillIQ>>()
    var topLearnerMutableData = MutableLiveData<List<TopLearner>>()
    val topSkill: Unit
        get() {
            APIClient.INSTANCE!!.topSkill!!.enqueue(object : Callback<List<SkillIQ?>?> {
                override fun onResponse(
                    call: Call<List<SkillIQ?>?>,
                    response: Response<List<SkillIQ?>?>
                ) {
                    skillMutableData.value = response.body() as List<SkillIQ>?
                }

                override fun onFailure(call: Call<List<SkillIQ?>?>, t: Throwable) {
                    //

                }
            })
        }
    val topLearner: Unit
        get() {
            APIClient.INSTANCE!!.topLearners!!.enqueue(object : Callback<List<TopLearner?>?> {
                override fun onResponse(
                    call: Call<List<TopLearner?>?>,
                    response: Response<List<TopLearner?>?>
                ) {
                    topLearnerMutableData.value = response.body() as List<TopLearner>?
                }

                override fun onFailure(call: Call<List<TopLearner?>?>, t: Throwable) {
                    //
                }
            })
        }
}

//private fun <T> Call<T>.enqueue(callback: Callback<List<TopLearner>>) {
//    TODO("Not yet implemented")
//}
//
//private fun <T> Call<T>.enqueue(callback: Callback<List<SkillIQ>>) {
//    TODO("Not yet implemented")
//}


/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val request = ServiceBuilder.buildService(TMDBEndPoints::class.java)
        val call = request.getMovies(getString(R.string.api_key))

        call.enqueue(object : Callback<PopularMovies>{
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful){
                    progress_bar.visibility = View.GONE
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = MoviesAdapter(response.body()!!.results)
                    }
                }
            }
            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
 */