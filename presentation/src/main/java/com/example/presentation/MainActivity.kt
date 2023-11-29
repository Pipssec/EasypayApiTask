package com.example.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.fragments.login.LoginFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(R.id.contActivity, LoginFragment())
            .commit()
    }
}