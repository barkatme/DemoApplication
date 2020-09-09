// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.jetpack.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentJetpackMenuBinding
import com.barkatme.demo.ui.base.BaseFragment
import com.barkatme.demo.ui.extensions.doOnClick
import kotlinx.android.synthetic.main.fragment_jetpack_menu.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class JetpackMenuFragment(private val layout: Int = R.layout.fragment_jetpack_menu) :
    BaseFragment(layout) {

    val viewModel: JetpackMenuViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentJetpackMenuBinding>(
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
        view.menu_camera_x.doOnClick { findNavController().navigate(R.id.action_jetpack_to_jetpackCameraXFragment) }
        view.menu_jetpack_paging.doOnClick { findNavController().navigate(R.id.action_jetpack_to_jetpackPagingFragment) }
    }
}