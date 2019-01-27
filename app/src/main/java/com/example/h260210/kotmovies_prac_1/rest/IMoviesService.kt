package com.example.h260210.kotmovies_prac_1.rest

import com.example.h260210.kotlinmovies.model.MoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IMoviesService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<MoviesResponse>
}