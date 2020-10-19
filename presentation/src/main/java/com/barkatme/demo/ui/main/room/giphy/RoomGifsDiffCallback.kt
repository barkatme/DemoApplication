// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.room.giphy

import androidx.recyclerview.widget.DiffUtil
import com.barkatme.demo.domain.model.giphy.Gif

class RoomGifsDiffCallback : DiffUtil.ItemCallback<Gif>() {
    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean =
        oldItem.title == newItem.title && oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean =
        oldItem == newItem

}