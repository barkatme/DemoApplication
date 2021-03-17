// Developed for %CLIENT% by Softeq Development Corporation
// http://www.softeq.com
package com.barkatme.demo.ui.main.pdm1.room.giphy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barkatme.demo.R
import com.barkatme.demo.domain.model.giphy.Gif
import com.barkatme.demo.ui.extensions.loadGif

class RoomGifsAdapter(private val onClick: (Gif) -> Unit) :
    ListAdapter<Gif, RoomGifsAdapter.ViewHolder>(RoomGifsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_room_gif, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(gif: Gif, onClick: (Gif) -> Unit) {
            itemView.findViewById<TextView>(R.id.tvGifTitle).text = gif.title
            gif.url.let {
                itemView.findViewById<ImageView>(R.id.imgImage).loadGif(it, gif.previewUrl)
            }
            itemView.setOnClickListener { onClick(gif) }
        }
    }
}