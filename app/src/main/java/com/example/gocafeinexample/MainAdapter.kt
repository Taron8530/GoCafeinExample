package com.example.gocafeinexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gocafeinexample.databinding.MoviePosterItemBinding

class MainAdapter(private var movieList : ArrayList<SearchResponseDataDTO.Search>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

//    private var movieList = ArrayList<SearchResponseDataDTO>()
    class ViewHolder(val binding: MoviePosterItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(MoviePosterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.movieNameItem.text = movieList[position].title
        holder.binding.movieReleaseDateItem.text = movieList[position].year
        Glide.with(holder.binding.root.context).load(movieList[position].poster).into(holder.binding.moviePosterItem)

    }
}