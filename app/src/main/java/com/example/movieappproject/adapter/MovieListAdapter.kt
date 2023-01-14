package com.example.movieappproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappproject.viewModel.MovieListViewModel
import com.example.movieappproject.R
import com.example.movieappproject.apiResponse.MovieModel
import com.indev.claraa.restApi.ClientApi

class MovieListAdapter (private val context: Context, var movieList: ArrayList<MovieModel>) : RecyclerView.Adapter<MovieListAdapter.MyViewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_movie_design, parent, false)
        return MyViewholder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        val currentItem = movieList[position]
        holder.tvTitle.text = currentItem.Title
        Glide.with(context).load(currentItem.Poster).into(holder.IVPoster)

    }

    fun setData(movieLists: ArrayList<MovieModel>) {
        this.movieList= movieLists
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MyViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView!!.findViewById(R.id.tvTitle)
        val IVPoster: ImageView = itemView!!.findViewById(R.id.IVPoster)

    }
}
