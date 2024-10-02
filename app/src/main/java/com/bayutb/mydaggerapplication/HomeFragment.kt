package com.bayutb.mydaggerapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.bayutb.core.app.AppRouter
import com.bayutb.core.app.Feature
import com.bayutb.core.app.navController
import com.bayutb.mydaggerapplication.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private val viewModelStoreOwner: ViewModelStoreOwner = this
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainComponent = (requireActivity() as MainActivity).mainComponent
        mainComponent.inject(this)

        viewModel = ViewModelProvider.create(
            owner = viewModelStoreOwner,
            factory = MainViewModel.Factory,
            extras = MutableCreationExtras().apply {
                set(
                    MainViewModel.DS_REPOSITORY_KEY,
                    mainComponent.provideDataStoreRepository()
                )
            }
        )[MainViewModel::class]
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