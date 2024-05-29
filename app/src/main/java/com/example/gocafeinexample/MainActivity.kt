package com.example.gocafeinexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.BulletSpan
import android.util.Log
import android.view.View
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
    private var movieList = ArrayList<SearchResponseDataDTO.Search>()
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
                if (lastVisibleItemPosition == totalItemCount - 1) {
                    mainActivityBinding.progressBar.visibility = View.VISIBLE
                }
                if (lastVisibleItemPosition >= threshold) {
                    mainActivityModel.getScrollMovie(BuildConfig.API_KEY){ result ->
                        Log.d(TAG, "onScrolled: $result")
                        if(result != null) {
                            for(i in result){
                                movieList.add(i)
                                adapter.notifyDataSetChanged()
                            }
                        }
                        mainActivityBinding.progressBar.visibility = View.GONE
                    }
                }
            }
        })

    }
    private fun recyclerViewInit(){
        mainActivityBinding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        searchMovie("star")
        mainActivityBinding.movieRecyclerView.adapter = adapter
        infinityScroll()
    }
    fun searchMovie(keyword : String){
        mainActivityModel.searchMovie(BuildConfig.API_KEY,keyword){ result ->
            Log.d(TAG, "recyclerViewInit: test")
            if (result != null) {
                adapter.setList(result)
                Log.d(TAG, "recyclerViewInit: $movieList")
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

}