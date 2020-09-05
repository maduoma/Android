package com.dodemy.moviemvvmarchitectecture.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dodemy.moviemvvmarchitectecture.model.EntityMovieOutputs;
import com.dodemy.moviemvvmarchitectecture.utils.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static final ApiInterface myInterface;
    private final MutableLiveData<EntityMovieOutputs> listOfMovies = new MutableLiveData<>();

    private static MovieRepository newsRepository;

    public static MovieRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return movieRepository;
    }

    public MovieRepository(){
        myInterface = RetrofitService.getInterface();
    }

    public MutableLiveData<EntityMovieOutputs> getListOfMoviesOutputs(String category, int page) {
        Call<EntityMovieOutputs> listOfMovieOut = myInterface.getListOfMovies(category, <API_KEY>, LANGUAGE, page);
        listOfMovieOut.enqueue(new Callback<EntityMovieOutputs>() {
            @Override
            public void onResponse(@NonNull Call<EntityMovieOutputs> call, @NonNull Response<EntityMovieOutputs> response) {
                listOfMovies.setValue(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<EntityMovieOutputs> call, @NonNull Throwable t) {
                listOfMovies.postValue(null);
            }
        });
        return listOfMovies;
    }
}
