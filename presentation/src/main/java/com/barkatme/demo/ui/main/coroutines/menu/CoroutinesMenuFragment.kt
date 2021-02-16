package com.barkatme.demo.ui.main.coroutines.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentCoroutiensMenuBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class CoroutinesMenuFragment(private val layout: Int = R.layout.fragment_coroutiens_menu) :
    Fragment(layout) {

    private val viewModel: CoroutinesMenuViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentCoroutiensMenuBinding>(
            layoutInflater,
            layout,
            container,
            false
        )
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            while (!viewModel.channel.isClosedForReceive) {
                val actionId = viewModel.channel.receive()
                findNavController().navigate(actionId)
            }
        }
    }
}