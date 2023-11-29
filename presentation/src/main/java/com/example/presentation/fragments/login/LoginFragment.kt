package com.example.presentation.fragments.login

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.blogstour.app.util.Lce
import com.example.data.model.loginresponse.LoginResponse
import com.example.presentation.databinding.FragmentLoginBinding
import com.example.presentation.fragments.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class LoginFragment() : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: SharedViewModel by activityViewModels()


    interface onSomeEventListener {
        fun sendResult()
    }

    private var someEventListener: onSomeEventListener? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        someEventListener = try {
            activity as onSomeEventListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$activity must implement onSomeEventListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStateViewModel()
        initButtonListener()


    }

    private fun initButtonListener() {
        val scope = CoroutineScope(Job())
        binding.checkButton.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            val password = binding.PasswordEditText.text.toString()
            scope.launch {
                viewModel.checkUser(login, password)
            }
        }
    }

    private fun observeStateViewModel() {
        viewModel
            .loginState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { lce ->
                when (lce) {
                    is Lce.Content -> {
                        verifyResult(lce)
                    }

                    is Lce.Error -> {
                        Toast.makeText(
                            requireContext(),
                            lce.exception.toString(),
                            Toast.LENGTH_LONG
                        ).show()

                    }

                    is Lce.Loading -> {

                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun verifyResult(lce: Lce.Content<LoginResponse>) {
        if (lce.data.success == "true") {
            viewModel.token = lce.data.response.token
            someEventListener?.sendResult()
        } else {
            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_LONG).show()
        }
    }

}