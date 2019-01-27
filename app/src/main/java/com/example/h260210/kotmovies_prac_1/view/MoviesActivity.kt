package com.example.h260210.kotmovies_prac_1.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.h260210.kotlinmovies.model.Movie
import com.example.h260210.kotmovies_prac_1.R
import com.example.h260210.kotmovies_prac_1.adapter.MoviesAdapter
import com.example.h260210.kotmovies_prac_1.common.CommonUtils
import com.example.h260210.kotmovies_prac_1.extensions.displayToast
import com.example.h260210.kotmovies_prac_1.rest.IMoviesService
import com.example.h260210.kotmovies_prac_1.rest.ServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {

    private val mMovies: MutableList<Movie> = mutableListOf()
    private var mMoviesAdapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        setAdapter()
        makeRequestForPopularMovies()
    }

    fun setAdapter() {
        mMoviesAdapter = MoviesAdapter(this, mMovies)
        recyler_movies.adapter = mMoviesAdapter
    }

    fun makeRequestForPopularMovies(){
        val moviesService = ServiceGenerator.createService(IMoviesService::class.java)
        moviesService.getPopularMovies(CommonUtils.getMoviesAPIKey())
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMoviesAdapter?.setData(it.moviesList!!)
            },{
                displayToast(it.message.toString())
            })

    }
}

