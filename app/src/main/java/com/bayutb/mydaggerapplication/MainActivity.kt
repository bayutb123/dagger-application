package com.bayutb.mydaggerapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bayutb.core.app.NavControllerProvider
import com.bayutb.core.di.getComponent
import com.bayutb.mydaggerapplication.databinding.ActivityMainBinding
import com.bayutb.mydaggerapplication.di.DaggerMainComponent
import com.bayutb.mydaggerapplication.di.MainComponent

class MainActivity : AppCompatActivity(), NavControllerProvider {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainComponent = DaggerMainComponent.builder()
            .appComponent(application.getComponent())
            .build()

        if (supportFragmentManager.findFragmentById(R.id.navHost) == null) {
            val navHostFragment = NavHostFragment.create(com.bayutb.core.R.navigation.nav_graph)
            supportFragmentManager.beginTransaction()
                .replace(R.id.navHost, navHostFragment)
                .setPrimaryNavigationFragment(navHostFragment)
                .commitNow()
        }
        navController = (supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment).navController
    }

    override fun getNavController(): NavController {
        return navController
    }
}
