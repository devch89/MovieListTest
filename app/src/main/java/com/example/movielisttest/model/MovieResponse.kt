package com.example.movielisttest.model

/**
 * in-memory data representation
 * We are going to use everytime we want to hold some data
 * if I want to create a person, name, address,
 * MovieLists, data files.
 * Just a simple place to hold data
 * Data by default provides us. String()
 * hashCode()
 * equals()
 * copy()
 * componentN()
 * it can not be inherited
 * it needs at least pne field/property/parameter in the constructor
 */
data class MovieResponse(
    //If we are using Json object everytime we see a {} that is one data class
    // Inside the data class we are to define the "key"/ attributes inside the constructor

val title : String,
val image : String,
val rating : Float,
val releaseYear : Int,
val genre : List<String>
)

//Make your own representation of the JSon array you are using.
class Response: ArrayList<MovieResponse>()
