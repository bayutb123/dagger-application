package com.bayutb.mydaggerapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bayutb.core.app.AppRouter
import com.bayutb.core.app.Feature
import com.bayutb.core.app.navController
import com.bayutb.mydaggerapplication.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: MainViewModelProvider
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observe()
    }

    private fun setupView() {
        binding.btnChat.setOnClickListener {
            AppRouter.go(requireActivity().navController(), Feature.CHAT)
        }
        binding.btnLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.user.collectLatest { user ->
                Log.d("Dagger", user.toString())
                if (user != null) {
                    binding.btnLogout.visibility = View.VISIBLE
                    binding.tvUserInfo.text = user.userName
                } else {
                    AppRouter.go(requireActivity().navController(), Feature.LOGIN, popBackStack = true)
                }
            }
        }
    }
}