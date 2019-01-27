package com.example.h260210.kotmovies_prac_1.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.h260210.kotlinmovies.model.Movie
import com.example.h260210.kotmovies_prac_1.R
import com.example.h260210.kotmovies_prac_1.common.CommonUtils
import com.example.h260210.kotmovies_prac_1.extensions.displayToast
import kotlinx.android.synthetic.main.list_movies.view.*

class MoviesAdapter(val context: Context, private val movies: MutableList<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_movies, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(moviesViewHolder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        moviesViewHolder.bindData(movie, position)
    }

    fun setData(movieList: List<Movie>) {
        movies.addAll(movieList)
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var selectedItem: Movie? = null
        private var selectedPosition: Int = -1


        init {
            itemView.setOnClickListener {
                context.displayToast("${selectedItem!!.title}: ${selectedPosition + 1}")
            }
        }

        fun bindData(movie: Movie?, position: Int) {
            movie?.let {
                CommonUtils.configureGlide(itemView.imageView_poster, it, context)
                itemView.textView_title.text = it.title
                itemView.textView_desc.text = it.overview
                itemView.imageView_ratings.setImageResource(CommonUtils.checkRatings(it.voteAverage!!))

                this.selectedItem = movie
                this.selectedPosition = position
            }
        }
    }
}