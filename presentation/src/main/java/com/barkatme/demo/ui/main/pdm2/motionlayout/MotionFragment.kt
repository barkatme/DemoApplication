package com.barkatme.demo.ui.main.pdm2.motionlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentMotionBinding
import com.barkatme.demo.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MotionFragment(val layout: Int = R.layout.fragment_motion) : BaseFragment(layout) {

    private var _binding: FragmentMotionBinding? = null
    private val binding get() = _binding!!
    val viewModel: MotionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}