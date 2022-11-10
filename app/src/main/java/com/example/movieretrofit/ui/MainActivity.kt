package com.example.movieretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.movieretrofit.R
import com.example.movieretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if (savedInstanceState == null){
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<MovieFragment>(R.id.containerView)
//            }
//        }
    }
}