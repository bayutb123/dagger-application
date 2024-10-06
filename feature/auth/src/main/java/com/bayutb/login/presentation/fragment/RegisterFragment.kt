package com.bayutb.login.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.navigation.fragment.findNavController
import com.bayutb.core.app.ComponentProvider
import com.bayutb.core.app.getParentNavBackStackEntry
import com.bayutb.core.di.getComponent
import com.bayutb.login.databinding.FragmentRegisterBinding
import com.bayutb.login.di.AuthComponent
import com.bayutb.login.di.DaggerAuthComponent
import com.bayutb.login.di.getAuthComponent
import com.bayutb.login.presentation.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    private val authComponent by lazy {
        val componentProvider by viewModels<ComponentProvider<AuthComponent>> (
            ownerProducer = { getParentNavBackStackEntry() },
            factoryProducer = {
                ComponentProvider.ComponentProviderFactory(
                    requireActivity().application.getAuthComponent()
                )
            }
        )
        componentProvider.component
    }

    private val viewModel: RegisterViewModel by viewModels(
        factoryProducer = { RegisterViewModel.Factory },
        extrasProducer = {
            MutableCreationExtras().apply {
                set(
                    RegisterViewModel.R_REPOSITORY_KEY,
                    authComponent.provideRegisterRepository()
                )
            }
        }
    )

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
                findNavController().navigateUp()
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