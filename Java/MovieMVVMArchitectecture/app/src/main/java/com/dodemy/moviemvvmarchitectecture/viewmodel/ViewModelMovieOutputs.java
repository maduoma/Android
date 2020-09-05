package com.dodemy.moviemvvmarchitectecture.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.dodemy.moviemvvmarchitectecture.model.EntityMovieOutputs;
import com.dodemy.moviemvvmarchitectecture.repositories.MovieRepository;

public class ViewModelMovieOutputs extends AndroidViewModel {
    private final MovieRepository repository;

    @SuppressWarnings({"FieldCanBeLocal"})
    private MutableLiveData<EntityMovieOutputs> listOfMovies = new MutableLiveData<>();
    public ViewModelMovieOutputs(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
    }
    public MutableLiveData<EntityMovieOutputs> getMoviesRepository(String category, int page) {
        listOfMovies = loadMoviesData(category, page);
        return listOfMovies;
    }
    private MutableLiveData<EntityMovieOutputs> loadMoviesData(String category, int page) {
        return repository.getListOfMoviesOutputs(category, page);
    }
}
