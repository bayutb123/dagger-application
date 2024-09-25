package com.bayutb.login.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bayutb.core.app.AppRouter
import com.bayutb.core.app.Feature
import com.bayutb.core.di.getComponent
import com.bayutb.login.R
import com.bayutb.login.databinding.ActivityLoginBinding
import com.bayutb.login.di.AuthComponent
import com.bayutb.login.di.DaggerAuthComponent

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    lateinit var authComponent: AuthComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        authComponent = DaggerAuthComponent.builder()
            .appComponent(application.getComponent())
            .build()
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.authFragmentContainer, LoginFragment())
            .commit()
    }

    fun setUpRegisterFragment() {
        val registerFragment = RegisterFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.authFragmentContainer, registerFragment)
            .addToBackStack(null)
            .commit()
    }

    fun backToLogin() {
        supportFragmentManager.popBackStack()
    }

    fun authorized() {
        AppRouter.go(this, Feature.HOME)
        finish()
    }
}