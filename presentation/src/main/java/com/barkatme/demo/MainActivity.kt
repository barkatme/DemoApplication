package com.barkatme.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.barkatme.data.NetRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val netRepository: NetRepository by inject()
        MainScope().launch {
            Toast.makeText(
                baseContext,
                netRepository.getComments()[0].toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
