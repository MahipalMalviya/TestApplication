package com.mahipal.testapplication.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahipal.testapplication.R
import com.mahipal.testapplication.mvvm.model.BaseResponse
import com.mahipal.testapplication.mvvm.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    var list = ArrayList<BaseResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                .create(MovieViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        movieViewModel.searchImages(this).observe(this, Observer {
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
}