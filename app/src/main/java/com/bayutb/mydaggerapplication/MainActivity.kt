package com.bayutb.mydaggerapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.fragment
import com.bayutb.core.app.Routes
import com.bayutb.core.app.addGraphFromXML
import com.bayutb.login.presentation.fragment.LoginFragment
import com.bayutb.login.presentation.fragment.RegisterFragment
import com.bayutb.mydaggerapplication.databinding.ActivityMainBinding
import com.bayutb.mydaggerapplication.di.MainComponent

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    val mainComponent: MainComponent by lazy {
        val componentViewModel by viewModels<ComponentViewModel> {
            ComponentViewModel.Factory
        }
        componentViewModel.mainComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.navHost)
        navController.graph = navController.createGraph(
            startDestination = Routes.Home
        ) {
            fragment<HomeFragment, Routes.Home>{
                label = "Home"
            }
            fragment<LoginFragment, Routes.Login> {
                label = "Login"
            }
            fragment<RegisterFragment, Routes.Register> {
                label = "Register"
            }

        }

        navController.addGraphFromXML(
            context = this@MainActivity,
            destName = "com.bayutb.chat.presentation.fragment.ChatListFragment"
        )

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
