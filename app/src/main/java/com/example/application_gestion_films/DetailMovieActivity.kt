package com.example.application_gestion_films

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.application_gestion_films.model.Film
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var itemImageDetail: ImageView
    private lateinit var itemTitleDetail: TextView
    private lateinit var itemDetailDetail: TextView
    private lateinit var itemDateDetail: TextView
    private var id :String = ""
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        id =  intent.getStringExtra("ID").toString()
        firestore.collection("film")
            .document(id)
            .get()
            .addOnCompleteListener {
                    result ->
                val film = result.getResult()
                itemTitleDetail.text =  film.get("name").toString()
                Picasso.get().load(film.get("poster_url").toString()).into(itemImageDetail)
                itemDetailDetail.text =  film.get("synopsis").toString()
                itemDateDetail.text =  film.get("release_date").toString()

            }
        itemImageDetail = findViewById(R.id.item_imageDetail)
        itemTitleDetail = findViewById(R.id.item_titleDetail)
        itemDetailDetail =  findViewById(R.id.item_detailDetail)
        itemDateDetail =  findViewById(R.id.item_dateDetail)
        itemTitleDetail.text = intent.getStringExtra("Name")
        itemDetailDetail.text = intent.getStringExtra("Synopsys")
        itemDateDetail.text =  intent.getStringExtra("Date")
        Picasso.get().load(intent.getStringExtra("Image")).into(itemImageDetail)
    }

}