package com.bayutb.mydaggerapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.bayutb.core.app.NavControllerProvider
import com.bayutb.mydaggerapplication.databinding.ActivityMainBinding
import com.bayutb.mydaggerapplication.di.MainComponent

class MainActivity : AppCompatActivity(), NavControllerProvider {
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
    }

    override fun getNavController(): NavController {
        return navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
