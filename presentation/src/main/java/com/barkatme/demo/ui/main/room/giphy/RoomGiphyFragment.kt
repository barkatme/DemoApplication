// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.room.giphy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.barkatme.demo.R
import com.barkatme.demo.databinding.FragmentRoomGiphyBinding
import com.barkatme.demo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_room_giphy.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomGiphyFragment(private val layout: Int = R.layout.fragment_room_giphy) :
    BaseFragment(layout) {

    val viewModel: RoomGiphyViewModel by viewModel()
    var initJob: Job? = null
    val adapter = RoomGifsAdapter { gifData -> viewModel.onGifClick(gifData) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentRoomGiphyBinding>(inflater, layout, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvRoomGifs.adapter = adapter
        rvRoomGifs.layoutManager = LinearLayoutManager(context)
        initJob = viewLifecycleOwner.lifecycleScope.launch {
            val gifs = viewModel.loadTrending()
            adapter.submitList(gifs)
        }
    }

    override fun onDestroy() {
        initJob?.cancel()
        super.onDestroy()
    }

}