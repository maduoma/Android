package com.dodemy.testingfragmentsinisolation.data.source

import com.dodemy.testingfragmentsinisolation.data.Movie

interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?
}