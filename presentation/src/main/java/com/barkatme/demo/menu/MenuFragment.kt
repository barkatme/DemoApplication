package com.barkatme.demo.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.barkatme.demo.R
import com.barkatme.demo.databinding.MenuFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MenuFragment : Fragment() {

    val viewModel: MenuViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<MenuFragmentBinding>(
            layoutInflater,
            R.layout.menu_fragment,
            container,
            false
        )
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.launch {
            while (!viewModel.channel.isClosedForReceive) {
                val actionId = viewModel.channel.receive()
                findNavController().navigate(actionId)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.channel.close()
        viewModel.cancel()
    }
}