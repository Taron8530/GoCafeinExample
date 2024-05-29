package com.example.gocafeinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.gocafeinexample.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    private val model by lazy { SecondActivityModel() }
    private lateinit var imdbID : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        imdbID = intent.getStringExtra("imdbID").toString()
        getMoviesDetailInformation()
    }

    fun getMoviesDetailInformation(){
        model.getMovieDetails(BuildConfig.API_KEY,imdbID) { result ->
            if(result != null){
                Glide.with(this).load(result.Poster).into(binding.posterImage)
                binding.movieName.text = result.Title
                binding.genre.text = result.Genre
                binding.director.text = "Director: ${result.Director}"
                binding.rating.text = "⭐️ ${result.Ratings.first().Value}"
                binding.release.text = result.Released
                binding.plot.text = result.Plot
                binding.secondContainer.visibility = View.VISIBLE
            }else{
                Toast.makeText(this,"잠시후 다시 시도해주세요.",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }


}