package com.barkatme.demo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barkatme.data.NetRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val netRepository: NetRepository by inject()

        val commentsAsync = netRepository.getCommentsAsync()

        MainScope().launch {
            logTextView.text = "loading..."
            val time = measureTimeMillis {
                print(commentsAsync.await()[0].toString())
            }
            print("duration: $time ms")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun print(string: Any) {
        logTextView.apply { text = text.toString() + "\n" + string.toString() }
    }
}
