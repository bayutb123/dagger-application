package com.bayutb.login.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.bayutb.core.app.AppRouter
import com.bayutb.core.app.ComponentProvider
import com.bayutb.core.app.Feature
import com.bayutb.core.app.getParentNavBackStackEntry
import com.bayutb.core.app.navController
import com.bayutb.core.di.getComponent
import com.bayutb.login.databinding.FragmentLoginBinding
import com.bayutb.login.di.AuthComponent
import com.bayutb.login.di.DaggerAuthComponent
import com.bayutb.login.di.getAuthComponent
import com.bayutb.login.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModelStoreOwner: ViewModelStoreOwner = this

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

    private val viewModel: LoginViewModel by viewModels(
        ownerProducer = { viewModelStoreOwner },
        factoryProducer = { LoginViewModel.Factory },
        extrasProducer = {
            MutableCreationExtras().apply {
                set(
                    LoginViewModel.L_REPOSITORY,
                    authComponent.provideLoginRepository()
                )
                set(
                    LoginViewModel.DS_REPOSITORY,
                    authComponent.provideDataStoreRepository()
                )
            }
        }
    )

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
            binding.errorMsg.visibility = if (loginUiState is LoginViewModel.LoginUiState.Failed) {
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