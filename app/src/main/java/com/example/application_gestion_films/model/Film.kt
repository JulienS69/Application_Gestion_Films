package com.example.application_gestion_films.model

class Film
    (var name : String = "", var release_date: String ="", var synopsis : String = "",)

interface OnGetDatabase {
    fun getFilm(film: Film)
}

class Database {
    var listener : OnGetDatabase? = null

    fun retrtrieveMovie(){
        listener?.getFilm(Film("", ""))
    }


}
