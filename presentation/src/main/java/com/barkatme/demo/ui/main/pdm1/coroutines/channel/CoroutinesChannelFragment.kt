package com.barkatme.demo.ui.main.pdm1.coroutines.channel

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentCoroutinesChannelsBinding
import com.barkatme.demo.ui.base.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class CoroutinesChannelFragment(private val layout: Int = R.layout.fragment_coroutines_channels) :
    BaseFragment(layout) {

    private val viewModel: CoroutinesChannelViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentCoroutinesChannelsBinding>(
            inflater,
            layout,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}