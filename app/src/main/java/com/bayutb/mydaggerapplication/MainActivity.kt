package com.bayutb.mydaggerapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bayutb.core.app.Feature
import com.bayutb.core.app.AppRouter
import com.bayutb.core.di.getComponent
import com.bayutb.mydaggerapplication.databinding.ActivityMainBinding
import com.bayutb.mydaggerapplication.di.DaggerMainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModelProvider: MainViewModelProvider
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DaggerMainComponent.builder()
            .appComponent(application.getComponent())
            .build()
            .inject(this)

        viewModel = ViewModelProvider(this, viewModelProvider)[MainViewModel::class]

        setupView()
        observe()
    }

    private fun setupView() {
        binding.btnChat.setOnClickListener {
            AppRouter.go(this, Feature.CHAT)
        }
    }

    private fun observe() {
        viewModel.user.observe(this) { user ->
            if (user == null) {
                AppRouter.go(this, Feature.LOGIN)
                finish()
            }
        }
    }
}