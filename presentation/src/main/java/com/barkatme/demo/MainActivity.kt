package com.barkatme.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.barkatme.data.NetRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainScope().launch {
            Toast.makeText(
                baseContext,
                NetRepository.test().toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
