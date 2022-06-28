package com.example.application_gestion_films

import android.R.attr.data
import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.application_gestion_films.model.Film
import com.squareup.picasso.Picasso


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var listFilms: List<Film> = listOf()

    private val images = intArrayOf(R.drawable.ic_easter_egg,R.drawable.ic_easter_egg,R.drawable.ic_easter_egg )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.car_view_item_layout_list, parent, false)

        return ViewHolder(v)
    }



    override fun getItemCount(): Int {
return listFilms.size
    }
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {

        holder.itemTitle.text = listFilms[position].name
        holder.itemDetail.text = listFilms[position].synopsis
        Picasso.get().load(listFilms[position].poster_url).into(holder.itemImage)
        holder.itemDate.text = listFilms[position].release_date

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {




        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        var itemDate: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)
            itemDate = itemView.findViewById(R.id.item_date)

            itemView.setOnClickListener {
                val position: Int = adapterPosition

                Toast.makeText(
                    itemView.context,
                    "Tu as cliqué sur le film ${listFilms[position].name}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

}

