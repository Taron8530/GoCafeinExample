package com.example.gocafeinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.gocafeinexample.databinding.ActivityMainBinding
import com.example.gocafeinexample.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val mainActivityBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainActivityModel by lazy { MainActivityModel() }
    private lateinit var movieList : ArrayList<SearchResponseDataDTO.Search>
    private val adapter by lazy { MainAdapter(movieList) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainActivityBinding.root)
        recyclerViewInit()
    }
    private fun recyclerViewInit(){
        mainActivityBinding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        mainActivityModel.searchMovie(BuildConfig.API_KEY,"star"){ result ->
            Log.d(TAG, "recyclerViewInit: test")
            if (result != null) {
                movieList = result
                Log.d(TAG, "recyclerViewInit: $movieList")
                mainActivityBinding.movieRecyclerView.adapter = adapter
            }
        }
    }
    fun searchMovie(){

    }

}