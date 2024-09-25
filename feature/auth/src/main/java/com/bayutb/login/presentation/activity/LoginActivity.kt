package com.bayutb.login.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bayutb.core.app.AppRouter
import com.bayutb.core.app.Feature
import com.bayutb.core.di.getComponent
import com.bayutb.login.databinding.ActivityLoginBinding
import com.bayutb.login.di.DaggerAuthComponent
import com.bayutb.login.presentation.viewmodel.LoginUiState
import com.bayutb.login.presentation.viewmodel.LoginViewModel
import com.bayutb.login.presentation.viewmodel.LoginViewModelFactory
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        DaggerAuthComponent.builder()
            .appComponent(application.getComponent())
            .build()
            .inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        setContentView(binding.root)

        setupView()
        observe()
    }

    private fun observe() {
        viewModel.uiState.observe(this) { loginUiState ->
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
        viewModel.navigateToHome.observe(this) { authorized ->
            if (authorized) {
                AppRouter.go(this, Feature.HOME)
                finish()
            }
        }
    }

    private fun setupView() {
        binding.btnLogin.setOnClickListener {
            val userName = binding.editTextTextEmailAddress.text
            val password = binding.editTextTextPassword.text

            viewModel.login(userName.toString(), password.toString())
        }
    }
}