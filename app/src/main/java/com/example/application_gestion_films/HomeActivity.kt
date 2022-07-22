package com.example.application_gestion_films

import android.content.ContentValues
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.application_gestion_films.model.Film
import com.example.application_gestion_films.model.OnGetDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.text.FieldPosition


class HomeActivity : AppCompatActivity(), OnGetDatabase, RecyclerAdapter.IONClickItem {

    override fun getFilm(film: Film) {

    }

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerAdapter? = null
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var listFilms = mutableListOf<Film>()
    private var id :String = ""
    private lateinit var imageView: ImageView

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
                   id =  it.id
                   film.id = id
                   film.name = it.data["name"].toString()
                   film.is_read= it.data["is_read"] as Boolean
                   film.synopsis = it.data["synopsis"].toString()
                   film.release_date = it.data["release_date"].toString()
                   film.poster_url = it.data["poster_url"].toString()
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

        (recyclerView.adapter as RecyclerAdapter).listener = this


    }


    override fun onClick(id: String) {
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }


}