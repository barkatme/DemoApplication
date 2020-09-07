// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.room.giphy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barkatme.data.model.giphy.GifData
import com.barkatme.demo.R
import com.barkatme.demo.ui.extentions.loadGif
import kotlinx.android.synthetic.main.item_room_gif.view.*

class RoomGifsAdapter(private val onClick: (GifData) -> Unit) :
    ListAdapter<GifData, RoomGifsAdapter.ViewHolder>(RoomGifsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_room_gif, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(gifData: GifData, onClick: (GifData) -> Unit) {
            itemView.tvGifTitle.text = gifData.title
            itemView.imgImage.loadGif(gifData.images.original.url)
            itemView.setOnClickListener { onClick(gifData) }
        }
    }
}