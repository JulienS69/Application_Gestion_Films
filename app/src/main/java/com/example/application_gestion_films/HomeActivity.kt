package com.example.application_gestion_films

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.application_gestion_films.model.Film
import com.example.application_gestion_films.model.OnGetDatabase
import com.google.firebase.firestore.FirebaseFirestore


class HomeActivity : AppCompatActivity(), OnGetDatabase {

    override fun getFilm(film: Film) {
        TODO("Not yet implemented")
    }

/*    private var listView:RecyclerView ?= null
    private var itemAdapters:ItemAdapters ?=null
    private var arrayList:ArrayList<ItemList> = ArrayList()*/
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerAdapter? = null


    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var listFilms = mutableListOf<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        layoutManager = LinearLayoutManager(this)
        val recyclerView:  RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager

        // Récupération de la liste de film et ajout de la liste récupérer dans la liste de film du RecyclerAdapter.
        firestore.collection("film")
            .get()
            .addOnSuccessListener {
                    result ->
               result.forEach {
                   val film = Film()
                  val name =  it.data["name"].toString()
                   val synopsis =  it.data["synopsis"].toString()
                   val release_date = it.data["release_date"].toString()
                   val poster_url = it.data["poster_url"].toString()
                   film.name = name
                   film.synopsis = synopsis
                   film.release_date = release_date
                   film.poster_url = poster_url
                   listFilms.add(film)

               }
                adapter?.listFilms = listFilms
                //Permet de recharger la page lorsque la liste est faite
                adapter?.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }

        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter

    }


    // Création des film (Cela évite de créer plusieurs ligne dans la bdd à la main)

/*    private fun createFilm(){
        findViewById<Button>(R.id.button).setOnClickListener {
            // Creation d'un nouveau film
            val film = hashMapOf(
                "director" to "",
                "name" to "",
                "poster_url" to "",
                "release_date" to "",
                "synopsis" to "",
            )


            firestore.collection("film")
                .add(film)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

        }
        return Toast.makeText(applicationContext, "good job", Toast.LENGTH_LONG).show()
    }*/


}