package com.example.application_gestion_films

import android.content.Intent
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
    var listener: IONClickItem? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_view_item_layout_list, parent, false)
        return ViewHolder(v, listener)
    }

    override fun getItemCount(): Int {
        return listFilms.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.update(listFilms[position])
    }

    interface IONClickItem {
        fun onClick(id: String)
    }


    inner class ViewHolder(itemView: View, listener: IONClickItem?) :
        RecyclerView.ViewHolder(itemView) {

        fun update(film: Film) {
            val itemImage: ImageView = itemView.findViewById(R.id.item_image)
            val itemTitle: TextView = itemView.findViewById(R.id.item_title)
            val itemDetail: TextView = itemView.findViewById(R.id.item_detail)
            val itemDate: TextView = itemView.findViewById(R.id.item_date)
            val isRead : ImageView = itemView.findViewById(R.id.is_read)
            val isReadText : TextView = itemView.findViewById(R.id.is_read_text_view)
            isRead.design(film.is_read)
            isReadText.design(film.is_read)
            itemTitle.text = film.name
            itemDetail.text = film.synopsis
            itemDate.text = film.release_date
            Picasso.get().load(film.poster_url).into(itemImage)
            itemView.setOnClickListener {
                listener?.onClick(film.id)
            }

        }


        fun TextView.design(isRead: Boolean){
            if(isRead){
                setText(R.string.is_read_text)
            }
        }
        fun ImageView.design(isRead :Boolean){
            if(isRead){
                setImageDrawable(getResources().getDrawable(R.drawable.ic_easter_egg))
            }
        }

    }

}

