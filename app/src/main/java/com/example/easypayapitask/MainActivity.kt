package com.example.easypayapitask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.easypayapitask.databinding.ActivityMainBinding
import com.example.presentation.fragments.login.LoginFragment
import com.example.presentation.fragments.main.MainMenuFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), LoginFragment.onSomeEventListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(R.id.contActivity, LoginFragment())
            .commit()
    }

    override fun sendResult() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.contActivity, MainMenuFragment())
            .commit()
    }
}