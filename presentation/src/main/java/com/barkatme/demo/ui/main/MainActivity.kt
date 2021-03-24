package com.barkatme.demo.ui.main

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.barkatme.demo.R
import com.barkatme.demo.databinding.ActivityMainBinding
import com.barkatme.demo.ui.base.BaseActivity


class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    private val navController
        get() = binding.navHostFragment.findNavController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
        setupSideMenu()
    }

    private fun setupSideMenu() {
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_chat -> navController.navigate(R.id.chat)
                R.id.nav_motionFragment -> navController.navigate(R.id.motionFragment)
                R.id.nav_patterns -> navController.navigate(R.id.patternsGraph)
                R.id.nav_room -> navController.navigate(R.id.roomGraph)
                R.id.nav_jetpack -> navController.navigate(R.id.jetpackGraph)
                R.id.nav_coroutines -> navController.navigate(R.id.coroutinesGraph)
                R.id.nav_auth -> navController.navigate(R.id.auth)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
}