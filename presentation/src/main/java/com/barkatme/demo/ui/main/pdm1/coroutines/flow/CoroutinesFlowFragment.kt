package com.barkatme.demo.ui.main.pdm1.coroutines.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentCoroutinesFlowBinding
import com.barkatme.demo.ui.base.BaseFragment
import com.barkatme.demo.ui.extensions.getQueryTextChangeStateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class CoroutinesFlowFragment(private val layout: Int = R.layout.fragment_coroutines_flow) :
    BaseFragment(layout) {

    val viewModel: CoroutinesFlowViewModel by viewModel()

    private var _binding: FragmentCoroutinesFlowBinding? = null
    val binding: FragmentCoroutinesFlowBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            layoutInflater,
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
        viewModel.setSearchFlow(binding.searchView.getQueryTextChangeStateFlow())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}