package com.barkatme.demo.ui.main.pdm2.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentChatBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.time.ExperimentalTime

@ExperimentalTime
class ChatFragment(private val layout: Int = R.layout.fragment_chat) : Fragment(layout) {

    private val viewModel: ChatViewModel by viewModel()
    private var _binding: FragmentChatBinding? = null
    val binding: FragmentChatBinding
        get() = _binding!!

    private val adapter: ChatAdapter = ChatAdapter { nickName ->
        viewModel.nickName.value == nickName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            layout,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvChat.layoutManager = LinearLayoutManager(context)
        binding.rvChat.adapter = adapter
        lifecycleScope.launchWhenResumed {
            viewModel.messages.consumeAsFlow()
                .filter { it.text.isNotEmpty() }
                .collect {
                    adapter.newMessage(it)
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}