package com.barkatme.demo.menu

import androidx.lifecycle.ViewModel
import com.barkatme.demo.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MenuViewModel : ViewModel(), CoroutineScope {

    val channel = Channel<Int>()
    
    val flowAction = R.id.action_menuFragment_to_flowFragment
    val channelAction = R.id.action_menuFragment_to_channelsFragment

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val job = Job()

    fun navigate(id: Int) {
        launch {
            channel.send(id)
        }
    }

}