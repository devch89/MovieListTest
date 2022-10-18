package com.example.movielisttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.utils.widget.MockView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var rvMovieList : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews(){
        rvMovieList = findViewById(R.id.movie_List)
        rvMovieList.adapter = MovieAdapter(generateData()) // generateData is going to be our dataSet that will pass our information
        rvMovieList.layoutManager = createLayoutManager() //allows arrangement of the layout elements
    }

    private fun createLayoutManager(): RecyclerView.LayoutManager? {
        /*
        vertical - column, false first item in data set
        vertical - column, true last item in data set is first
        horizontal - row, false  first item in data set
        horizontal - false first item in data set
         */
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //works like any other grid
        val gridLayoutManager = GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)

        //used for like a collage of pictures
        // we do not need this(cOntext) unless we are going to implement our own custom views
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL)
        return gridLayoutManager

    }

    private fun generateData(): List<String> {
        // we use map to transform each element from Int to String
    return (0..10).map {
        "Movie with the name: $it"
    }
    }
}
/*
STEPS TO CREATING A RECYCLERVIEW
1- Create an Item_layout (layout xml file)
2- Subclass of RecyclerView.ViewHolder
3- Subclass of RecyclerView.Adapter
4- Configure the movieList view
    4.a.- setAdapter
    4.b.- setLayoutManager
 */