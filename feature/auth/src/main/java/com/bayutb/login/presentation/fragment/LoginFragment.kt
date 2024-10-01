package com.bayutb.login.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bayutb.core.app.AppRouter
import com.bayutb.core.app.Feature
import com.bayutb.core.app.navController
import com.bayutb.core.di.getComponent
import com.bayutb.login.databinding.FragmentLoginBinding
import com.bayutb.login.di.DaggerAuthComponent
import com.bayutb.login.presentation.viewmodel.LoginUiState
import com.bayutb.login.presentation.viewmodel.LoginViewModel
import com.bayutb.login.presentation.viewmodel.LoginViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        DaggerAuthComponent.builder()
            .appComponent(requireActivity().application.getComponent())
            .build().inject(this)

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

            if (userName.isNotBlank() && password.isNotBlank()) {
                viewModel.login(userName.toString(), password.toString())
            }
        }
        binding.btnRegisterNow.setOnClickListener{
            AppRouter.go(requireActivity().navController(), Feature.REGISTER)
        }
    }

    private fun observe() {
        viewModel.uiState.observe(viewLifecycleOwner) { loginUiState ->
            binding.errorMsg.visibility = if (loginUiState is LoginUiState.Failed) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loggedIn.collectLatest { user->
                if (user != null) {
                    AppRouter.go(requireActivity().navController(), Feature.HOME, popBackStack = true)
                }
            }
        }
    }

}