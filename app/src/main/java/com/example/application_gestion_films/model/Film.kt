package com.example.application_gestion_films.model

class Film
    (var name : String = "", var poster_url: String="https://fr.web.img4.acsta.net/pictures/15/10/13/15/12/514297.jpg", var id: String="",  var is_read: Boolean=false, var release_date: String ="", var synopsis : String = "",)

interface OnGetDatabase {
    fun getFilm(film: Film)
}

class Database {
    var listener : OnGetDatabase? = null

    fun retrtrieveMovie(){
        listener?.getFilm(Film("", ""))
    }

}
