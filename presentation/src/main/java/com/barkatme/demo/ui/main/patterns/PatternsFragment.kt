package com.barkatme.demo.ui.main.patterns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentPatternsBinding
import com.barkatme.demo.ui.base.BaseFragment
import com.barkatme.demo.ui.extensions.doOnClick

class PatternsFragment(private val layout: Int = R.layout.fragment_patterns) :
    BaseFragment(layout) {

    private var _binding: FragmentPatternsBinding? = null
    val binding: FragmentPatternsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            layout,
            container,
            false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.tvDecorator.doOnClick { findNavController().navigate(R.id.action_patterns_to_decorator) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}