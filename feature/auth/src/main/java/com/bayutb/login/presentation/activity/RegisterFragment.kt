package com.bayutb.login.presentation.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bayutb.login.databinding.FragmentRegisterBinding
import com.bayutb.login.presentation.viewmodel.RegisterViewModel
import com.bayutb.login.presentation.viewmodel.RegisterViewModelFactory
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {

    @Inject lateinit var viewModelFactory: RegisterViewModelFactory
    private lateinit var viewModel: RegisterViewModel

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as LoginActivity).authComponent.inject(this)
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class]
        setupView()
    }

    private fun observe() {
        viewModel.navigateToLogin.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                (activity as LoginActivity).backToLogin()
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

    companion object {
        @JvmStatic
        fun newInstance() =
            RegisterFragment()
    }
}