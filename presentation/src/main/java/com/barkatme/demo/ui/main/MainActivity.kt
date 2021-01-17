package com.barkatme.demo.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.barkatme.demo.R
import com.barkatme.demo.databinding.ActivityMainBinding
import com.barkatme.demo.ui.base.BaseActivity


class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    @Suppress("CAST_NEVER_SUCCEEDS")
    private val ActivityMainBinding.navController
        get() = (binding.navHostFragment as (NavHostFragment)).navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        NavigationUI.setupWithNavController(
            binding.bottomNavigation,
            binding.navController
        )
    }
}