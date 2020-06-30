package com.barkatme.github

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barkatme.data.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        User("test user")
    }
}