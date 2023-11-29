package com.example.presentation.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.presentation.databinding.FragmentMainMenuBinding
import com.example.presentation.fragments.SharedViewModel
import kotlinx.coroutines.runBlocking

class MainMenuFragment : Fragment() {
    private lateinit var binding: FragmentMainMenuBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runBlocking {
            viewModel.getPayments()
        }
        binding.logoutButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            viewModel.token = ""
        }
//        binding.composeView.setContent {
//            MaterialTheme {
//
//            }
//        }

    }
    
}