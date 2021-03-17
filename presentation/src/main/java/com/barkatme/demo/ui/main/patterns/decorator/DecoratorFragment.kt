package com.barkatme.demo.ui.main.patterns.decorator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentDecoratorBinding
import com.barkatme.demo.ui.base.BaseFragment

class DecoratorFragment(private val layout: Int = R.layout.fragment_decorator) : BaseFragment(layout) {
    private var _binding: FragmentDecoratorBinding? = null
    val binding: FragmentDecoratorBinding
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}