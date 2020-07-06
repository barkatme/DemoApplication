package com.barkatme.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.barkatme.data.NetRepository
import com.barkatme.data.User
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    val user: User by inject(named("parametrized")) { parametersOf("test user name") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val netRepository: NetRepository by inject()
        MainScope().launch {
            Toast.makeText(
                baseContext,
                netRepository.test().toString() + "\n\nUSERNAME = ${user.name}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
