package com.example.movielisttest

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//pass the dataSet(any name is ok) of information that will be used in this adapter <DataType>
class MovieAdapter(private val dataSet: List<String>): RecyclerView.Adapter<MovieViewHolder>() {

    /**
     * Used to Create the ViewHolder.
     * This will be called only Once.
     * If the structure of the ViewHolder changes, this one will be called again
     *
     * Context - Get @LayoutInflator.from(context)
     * Context needs to be from Base Context(ContextThemeWrapper)
     * base context provides Themes, Resources and the Inflator.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        /*

        Inside the view we need to create a view where the
        This says that the recyclerview is going to be inflated inside of another view(Recycler View)
        we are calling a layout
        false lets the inflator know when to inflate. If it is later then we pass false. Otherwise do not add third argument
         */
        return MovieViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_layout, parent, false))
    }

    /*
     Where the data is going to be displayed based on that given position
    * Here we are connecting the ViewHolder with the data
    * We have the reference of the View Holder and the index
    * no return needed
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if(position % 2 == 0) // for the even positions color dark grey
        {
            holder.itemView.setBackgroundColor(Color.DKGRAY) //.itemView because we want to color the view.
        }
            holder.tvMovieTitle.text = dataSet[position] // this grabs the position index of the dataSet
    }

    /*
     * How many elements I am going to display in this recycler
     * The size of the dataset is the number of elements
     * return the number of elements
     */

    override fun getItemCount(): Int {
        return dataSet.size
    }
}