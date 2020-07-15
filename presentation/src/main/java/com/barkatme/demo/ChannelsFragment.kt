package com.barkatme.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.barkatme.demo.databinding.ChannelsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChannelsFragment : Fragment() {

    companion object {
        val TAG = ChannelsFragment::class.simpleName
    }

    private val viewModel: ChannelsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ChannelsFragmentBinding>(
            inflater,
            R.layout.channels_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}