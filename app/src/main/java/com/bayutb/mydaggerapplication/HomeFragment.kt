package com.bayutb.mydaggerapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bayutb.core.app.AppRouter
import com.bayutb.core.app.Feature
import com.bayutb.core.app.navController
import com.bayutb.mydaggerapplication.databinding.FragmentHomeBinding
import com.bayutb.mydaggerapplication.di.MainComponent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private val viewModelStoreOwner: ViewModelStoreOwner = this

    private val mainComponent by lazy {
        (requireActivity() as MainActivity).mainComponent
    }

    private val viewModel by viewModels<MainViewModel> (
        ownerProducer = { viewModelStoreOwner },
        factoryProducer = { MainViewModel.Factory },
        extrasProducer = { MutableCreationExtras().apply {
            set(
                MainViewModel.DS_REPOSITORY_KEY,
                mainComponent.provideDataStoreRepository()
            )
        }}
    )

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Dagger", mainComponent.toString())
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

    override fun onResume() {
        super.onResume()
        Log.d("Dagger", viewModel.toString())
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.user.collectLatest { user ->
                if (user != null) {
                    binding.btnLogout.visibility = View.VISIBLE
                    binding.tvUserInfo.text = user.userName
                } else {
                    AppRouter.go(
                        requireActivity().navController(),
                        Feature.LOGIN,
                        popBackStack = true
                    )
                }
            }
        }
    }
}