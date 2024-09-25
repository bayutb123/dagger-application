package com.bayutb.login.presentation.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bayutb.core.app.AppRouter
import com.bayutb.core.app.Feature
import com.bayutb.login.R
import com.bayutb.login.databinding.FragmentLoginBinding
import com.bayutb.login.databinding.FragmentRegisterBinding
import com.bayutb.login.di.DaggerAuthComponent
import com.bayutb.login.presentation.viewmodel.LoginUiState
import com.bayutb.login.presentation.viewmodel.LoginViewModel
import com.bayutb.login.presentation.viewmodel.LoginViewModelFactory
import javax.inject.Inject

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        (activity as LoginActivity).authComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class]
        setupView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun setupView() {
        binding.btnLogin.setOnClickListener {
            val userName = binding.editTextTextEmailAddress.text
            val password = binding.editTextTextPassword.text

            viewModel.login(userName.toString(), password.toString())
        }
        binding.btnRegisterNow.setOnClickListener{
            (activity as LoginActivity).setUpRegisterFragment()
        }
    }

    private fun observe() {
        viewModel.uiState.observe(viewLifecycleOwner) { loginUiState ->
            when (loginUiState) {
                is LoginUiState.Idle -> {
                    binding.btnLogin.isEnabled = true
                }
                is LoginUiState.Failed -> {
                    binding.btnLogin.isEnabled = true
                }
                is LoginUiState.Loading -> {
                    binding.btnLogin.isEnabled = false
                }
                is LoginUiState.Success -> {
                    binding.btnLogin.isEnabled = true
                }
            }
        }
        viewModel.navigateToHome.observe(viewLifecycleOwner) { authorized ->
            if (authorized) {
                (activity as LoginActivity).authorized()
            }
        }
    }

}