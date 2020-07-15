package com.barkatme.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.barkatme.demo.channel.ChannelsFragment
import com.barkatme.demo.flow.FlowFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flow.setOnClickListener {
            supportFragmentManager.commit {
                replace<FlowFragment>(R.id.frameLayout, null, null)
                addToBackStack(FlowFragment.TAG)
            }
        }
        channels.setOnClickListener {
            supportFragmentManager.commit {
                replace<ChannelsFragment>(R.id.frameLayout, null, null)
                addToBackStack(ChannelsFragment.TAG)
            }
        }
    }
}