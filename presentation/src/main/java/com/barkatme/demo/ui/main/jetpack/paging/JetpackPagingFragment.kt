// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.jetpack.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentJetpackPagingBinding
import com.barkatme.demo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_jetpack_paging.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class JetpackPagingFragment(private val layout: Int = R.layout.fragment_jetpack_paging) :
    BaseFragment(layout) {

    val viewModel: JetpackPagingViewModel by viewModel()
    val adapter = GifPageListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentJetpackPagingBinding>(
            inflater,
            layout,
            container,
            false
        )
        binding.viewModel = viewModel
        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.gifAllList.observe(viewLifecycleOwner) { adapter.submitList(it) }
        view.rvJetpackGifs.adapter = adapter
        view.rvJetpackGifs.layoutManager = LinearLayoutManager(requireContext())
        view.svJetpackGifs.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true
            override fun onQueryTextChange(query: String?): Boolean {
                viewModel.filterTextAll.value = "%$query%"
                return true
            }
        })
    }

}