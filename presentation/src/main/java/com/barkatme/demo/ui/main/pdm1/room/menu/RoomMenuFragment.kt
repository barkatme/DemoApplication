// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.pdm1.room.menu

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.barkatme.demo.R
import com.barkatme.demo.ui.base.BaseFragment
import com.barkatme.demo.ui.extensions.doOnClick

class RoomMenuFragment(layout: Int = R.layout.fragment_room_menu) : BaseFragment(layout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.menu_giphy).doOnClick {
            findNavController().navigate(R.id.action_room_to_roomGiphyFragment)
        }
    }
}