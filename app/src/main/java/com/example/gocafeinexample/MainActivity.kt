package com.example.gocafeinexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    fun infinityScroll(){
        mainActivityBinding.movieRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val threshold = totalItemCount / 2
                if (lastVisibleItemPosition >= threshold) {
//                    loadData()
                }
            }
        })

    }
    private fun recyclerViewInit(){
        mainActivityBinding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        mainActivityModel.searchMovie(BuildConfig.API_KEY,"star"){ result ->
            Log.d(TAG, "recyclerViewInit: test")
            if (result != null) {
                movieList = result
                Log.d(TAG, "recyclerViewInit: $movieList")
                mainActivityBinding.movieRecyclerView.adapter = adapter
                adapter.setClickListener(object : MovieItemClickListener{
                    override fun posterClick(imdbID: String) {
                        val intent = Intent(applicationContext,SecondActivity :: class.java)
                        intent.putExtra("imdbID",imdbID)
                        startActivity(intent)
                    }
                })
            }else {
                Toast.makeText(this,"현재 오류가 발생했습니다. 빠른 시일내에 처리하도록 하겠습니다.",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun searchMovie(){

    }

}