// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.pdm1.room.giphy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentRoomGiphyBinding
import com.barkatme.demo.ui.base.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomGiphyFragment(private val layout: Int = R.layout.fragment_room_giphy) :
    BaseFragment(layout) {

    val viewModel: RoomGiphyViewModel by viewModel()
    private val adapter = RoomGifsAdapter { gif -> viewModel.onGifClick(gif) }

    private var _binding: FragmentRoomGiphyBinding? = null
    val binding: FragmentRoomGiphyBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            DataBindingUtil.inflate<FragmentRoomGiphyBinding>(inflater, layout, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRoomGifs.adapter = adapter
        binding.rvRoomGifs.layoutManager = LinearLayoutManager(context)
        viewModel.createGifsLiveData(binding.svGifs.getQueryTextChangeStateFlow())
            .asFlow()
            .onEach { adapter.submitList(it) }
            .launchIn(lifecycleScope)
    }
}