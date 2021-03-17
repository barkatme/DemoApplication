package com.barkatme.demo.ui.main.pdm2.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentAuthBinding
import com.barkatme.demo.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment(private val layout: Int = R.layout.fragment_auth) : BaseFragment(layout) {

    private val viewModel: AuthViewModel by viewModel()

    private var _binding: FragmentAuthBinding? = null
    val binding: FragmentAuthBinding
        get() = _binding!!

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

    override fun onStart() {
        super.onStart()
        viewModel.onAuthCompleted = { token ->
            Log.d(AuthViewModel.TAG, "auth token: $token")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}