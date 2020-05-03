package com.dodemy.android.movieswithmvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dodemy.android.movieswithmvvm.model.Movie;
import com.dodemy.android.movieswithmvvm.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private LiveData<List<Movie>> allMovies;

    public MovieViewModel(@NonNull Application application) {
        super(application);

        movieRepository = new MovieRepository(application);
        allMovies = movieRepository.getAllMovies();
    }

    public void insertMovie(Movie movie) {
        movieRepository.insertMovie(movie);
    }

    public void updateMovie(Movie movie) {
        movieRepository.updateMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.deleteMovie(movie);
    }

    public void deleteAllMovies() {
        movieRepository.deleteAllMovies();
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }
}
