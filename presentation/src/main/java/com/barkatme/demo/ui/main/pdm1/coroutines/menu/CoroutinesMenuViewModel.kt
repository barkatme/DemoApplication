package com.barkatme.demo.ui.main.pdm1.coroutines.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barkatme.demo.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class CoroutinesMenuViewModel : ViewModel() {

    val channel = Channel<Int>()

    val flowAction = R.id.action_menuFragment_to_flowFragment
    val channelAction = R.id.action_menuFragment_to_channelsFragment

    fun navigate(id: Int) {
        viewModelScope.launch {
            channel.send(id)
        }
    }

}