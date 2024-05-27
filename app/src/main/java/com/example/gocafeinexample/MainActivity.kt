package com.example.gocafeinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gocafeinexample.databinding.ActivityMainBinding
import com.example.gocafeinexample.BuildConfig

class MainActivity : AppCompatActivity() {
    private val mainActivityBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainActivityBinding.root)

        val test = BuildConfig.API_KEY
    }

}