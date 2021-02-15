package com.barkatme.demo.ui.main.chat


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barkatme.demo.R
import com.barkatme.demo.domain.model.demo.Message
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

@ExperimentalTime
class ChatAdapter(
    private val checkNickName: (String) -> Boolean = { false }
) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    private val messages: ArrayList<Message> = arrayListOf()

    fun newMessage(message: Message) {
        if (messages.isNotEmpty()
            && messages.last().time.milliseconds.inDays.toInt() == message.time.milliseconds.inDays.toInt()
        ) {
            message.showDate = false
        }
        messages.add(message)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int =
        if (checkNickName(messages[position].nickName)) ViewHolder.ME else ViewHolder.OTHER

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = when (viewType) {
            ViewHolder.ME -> R.layout.item_message_me
            ViewHolder.OTHER -> R.layout.item_message_other
            else -> R.layout.item_message_other
        }
        return ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var messageText: TextView = itemView.findViewById(R.id.message_text)
        var dateText: TextView = itemView.findViewById(R.id.date)
        var nameText: TextView = itemView.findViewById(R.id.nick_name)
        var timeText: TextView = itemView.findViewById(R.id.message_time)

        @SuppressLint("SetTextI18n")
        fun bind(message: Message) {
            messageText.text = message.text
            dateText.text = dateFormatter.format(Date(message.time))
            nameText.text = message.nickName
            dateText.takeIf { !message.showDate }?.visibility = View.GONE
            timeText.text = timeFormatter.format(Date(message.time))
        }

        companion object {
            const val OTHER = 0
            const val ME = 1

            val dateFormatter: SimpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
            val timeFormatter: SimpleDateFormat = SimpleDateFormat("hh:mm:ss", Locale.ENGLISH)
        }

    }
}