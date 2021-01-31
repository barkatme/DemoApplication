// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.jetpack.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barkatme.demo.R
import com.barkatme.demo.domain.model.giphy.Gif
import com.barkatme.demo.ui.extensions.loadGif
import com.barkatme.demo.ui.main.room.giphy.RoomGifsDiffCallback

class GifPageListAdapter :
    PagedListAdapter<Gif, GifPageListAdapter.ViewHolder>(RoomGifsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_room_gif, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(gif: Gif) {
            itemView.findViewById<TextView>(R.id.tvGifTitle).text = gif.title
            gif.url.let {
                itemView.findViewById<ImageView>(R.id.imgImage).loadGif(it, gif.previewUrl)
            }
        }
    }
}