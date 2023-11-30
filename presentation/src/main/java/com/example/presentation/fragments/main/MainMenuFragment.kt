package com.example.presentation.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.presentation.databinding.FragmentMainMenuBinding
import com.example.presentation.fragments.SharedViewModel
import com.example.presentation.fragments.compose.PaymentsContent
import kotlinx.coroutines.launch

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
        binding.logoutButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.composeView.setContent {
            MaterialTheme {
                PaymentsContent(viewModel = viewModel)
            }
        }
    }
    private fun getData(){
        lifecycleScope.launch {
            viewModel.getPayments()
        }
    }

    private fun observeStateViewModel() {
        lifecycleScope.launch {
            viewModel
                .paymentsState.collect {
//                    renderData(it)
                }
        }
    }



//    private fun renderData(data: Lce<PaymentsResponse>){
//        binding.composeView.setContent {
//            MaterialTheme {
//                PaymentsContent(data)
//            }
//        }
//    }

}