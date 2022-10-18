package com.example.movielisttest


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



/* In order for view Holder to hold a view we need to pass the View into the parent
 * we can not pass view:TextView because the adapter needs to know if the hierarchy changed.
* In this case we must pass the entire view.
 */
class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    /* we define view.find... so that we can reference the specific view. If not we will experience a crash
    * or have to define the view we are pointing to at every declaration.
    * in the super class constructor we are defining any view, here we are declaring such view
    */
    val tvMovieTitle : TextView = view.findViewById(R.id.tv_movie_title)

}