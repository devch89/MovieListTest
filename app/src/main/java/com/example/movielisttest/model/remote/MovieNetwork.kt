package com.example.movielisttest.model.remote

import android.net.Uri
import com.example.movielisttest.model.MovieResponse
import com.example.movielisttest.model.Response
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MovieNetwork {
    // uses the companion object because we are going to declare the
// variables we are going to be using to build the network
    // base url - https://gist.githubusercontent.com/
// endpoints of url - AntoninoAN/f3fa4b2260c51a5f80904c747009289e/raw/6576691177f6b093afd3bf2bbc5e936b62d50721/MovieGist
    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com/"
        const val ENDPOINT =
            "AntoninoAN/f3fa4b2260c51a5f80904c747009289e/raw/6576691177f6b093afd3bf2bbc5e936b62d50721/MovieGist"
        val uriPath = Uri.parse("$BASE_URL$ENDPOINT")
        val url = URL(uriPath.toString())
    }

    //opening connection and getting the data
    //it is outside of companion because we want to create an instance of that class
    fun getMovielist() : List<MovieResponse>{
        //we are casting openConnectiong which has an url connection to http connection.
        // us the keyword as
        val httpURLConnection = url.openConnection() as HttpURLConnection

        //how many seconds I am going to wait for reading the data and connection to server in ms
        httpURLConnection.readTimeout = 10000
        httpURLConnection.connectTimeout = 15000
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.doInput = true

        httpURLConnection.connect()

            httpURLConnection.inputStream.run {
                deserialize(this)
            }.let {
                return parseToMovieResponse(it)
            }
    }

    fun deserialize(iS: InputStream): String {
        val builder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(iS))
        var line: String? = reader.readLine()
        while (line != null) {
            builder.append(line)
            builder.append("\n")
            line = reader.readLine()
        }
        if (builder.isEmpty()) {
            // because we return a string. was originally returning null but error. String needs to be returned or we can add ? to string
            return "N/A"
        }
        return builder.toString()
    }

    fun parseToMovieResponse(deserialize: String) : List<MovieResponse> {
        val response = JSONArray(deserialize)
        val movieResponseList = Response()
        val listOfMovies = mutableListOf<MovieResponse>()

        for (i in 0 until response.length()) { // index = [0, 1, 2 ...response.length - 1]
        val movieElement = response.getJSONObject(i)
            val movieResponse = MovieResponse(
                // called naming arguments to change order from Movie Respinse file
                rating = movieElement.getDouble("rating").toFloat(),
                releaseYear = movieElement.getInt("releaseYear"),
                title = movieElement.getString("title"),
                image = movieElement.getString("image"),
                genre = movieElement.getJSONArray("genre").parseJsonArrayToList()
            )
            movieResponseList.add(movieResponse)
            listOfMovies.add(movieResponse)
        }
        return listOfMovies
    }
    // we need to parse our genre so we can iterate through the list
    // we use extention functions give us the ability to append or add extra functionality to given types of target.
    // The target in this case is the JsonArray that is in this target.
    private fun JSONArray.parseJsonArrayToList(): List<String> {
        val result = mutableListOf<String>()
        for (i in 0 until this.length()){
            result.add(this.getString(i))
        }
        return result
    }
}
