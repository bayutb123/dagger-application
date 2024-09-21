package com.bayutb.mydaggerapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bayutb.core.app.Feature
import com.bayutb.core.app.AppRouter
import com.bayutb.mydaggerapplication.databinding.ActivityMainBinding
import com.bayutb.mydaggerapplication.di.DaggerMainComponent
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var retrofit: Retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DaggerMainComponent.builder()
            .appComponent(application.getComponent())
            .build()
            .inject(this)

        Log.d("Dagger", retrofit.toString())

        binding.btnChat.setOnClickListener {
            AppRouter.go(this, Feature.CHAT)
        }
    }
}