package com.bayutb.login.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bayutb.core.app.navController
import com.bayutb.core.di.getComponent
import com.bayutb.login.databinding.FragmentRegisterBinding
import com.bayutb.login.di.DaggerAuthComponent
import com.bayutb.login.presentation.viewmodel.RegisterViewModel
import com.bayutb.login.presentation.viewmodel.RegisterViewModelFactory
import javax.inject.Inject

class RegisterFragment : Fragment() {

    @Inject lateinit var viewModelFactory: RegisterViewModelFactory
    private val viewModel: RegisterViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAuthComponent.builder()
            .appComponent(requireActivity().application.getComponent())
            .build().inject(this)
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        setupView()
    }

    private fun observe() {
        viewModel.navigateToLogin.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                requireActivity().navController().popBackStack()
            }
        }
    }

    private fun setupView() {
        binding.btnRegister.setOnClickListener {
            val userName = binding.etUserName.text.toString()
            val password = binding.etPassword.text.toString()
            if (userName.isNotBlank() && password.isNotBlank()) {
                viewModel.register(userName, password)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }
}