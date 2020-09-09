package com.mahipal.testapplication.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahipal.testapplication.R
import com.mahipal.testapplication.mvvm.model.BaseResponse
import com.mahipal.testapplication.mvvm.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var movieViewModel: MovieViewModel
    private var list = ArrayList<BaseResponse>()
    private var movieName:String? = "Ironman"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                .create(MovieViewModel::class.java)

        search_view.setOnQueryTextListener(this)
        getMoviesBySearch(movieName)
    }

    private fun getMoviesBySearch(title:String?) {
        movieViewModel.searchMovies(this,title).observe(this, Observer {
            if (list.isNotEmpty()) {
                list.clear()
            }
            list.add(it)

            val layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            rv_movie_list.layoutManager = layoutManager

            val movieAdapter =
                MovieAdapter(this, list)
            rv_movie_list.adapter = movieAdapter
        })
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query?.length?:0 > 0) {
            movieName = query
            getMoviesBySearch(query)
        }
        return true
    }
}