// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.room.giphy

import androidx.recyclerview.widget.DiffUtil
import com.barkatme.data.model.giphy.GifData

class RoomGifsDiffCallback : DiffUtil.ItemCallback<GifData>() {
    override fun areItemsTheSame(oldItem: GifData, newItem: GifData): Boolean =
        oldItem.title == newItem.title && oldItem.images.original.url == newItem.images.original.url

    override fun areContentsTheSame(oldItem: GifData, newItem: GifData): Boolean =
        oldItem == newItem

}