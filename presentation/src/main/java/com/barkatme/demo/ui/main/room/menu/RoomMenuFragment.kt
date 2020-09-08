// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.room.menu

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.barkatme.demo.R
import com.barkatme.demo.ui.base.BaseFragment
import com.barkatme.demo.ui.extensions.doOnClick
import kotlinx.android.synthetic.main.fragment_room_menu.view.*

class RoomMenuFragment(layout: Int = R.layout.fragment_room_menu) : BaseFragment(layout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.menu_giphy.doOnClick { findNavController().navigate(R.id.action_room_to_roomGiphyFragment) }
    }
}