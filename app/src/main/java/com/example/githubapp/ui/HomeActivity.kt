package com.example.githubapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.githubapp.databinding.ActivityHomeScreenBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeScreenBinding.inflate(LayoutInflater.from(this))
    }
}