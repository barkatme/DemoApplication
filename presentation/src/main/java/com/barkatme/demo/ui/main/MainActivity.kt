package com.barkatme.demo.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.barkatme.demo.R
import com.barkatme.demo.databinding.ActivityMainBinding
import com.barkatme.demo.ui.base.BaseActivity
import com.barkatme.demo.ui.widgets.WidgetsActivity


class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        NavigationUI.setupWithNavController(
            binding.bottomNavigation,
            binding.navHostFragment.findNavController()
        )
        startActivity(Intent(baseContext, WidgetsActivity::class.java))
    }
}