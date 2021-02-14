package com.barkatme.demo.ui.main.chat


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barkatme.demo.R
import com.barkatme.demo.domain.model.demo.Message
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds

@ExperimentalTime
class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    private val myNickName: String = ""
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
        if (messages[position].nickName == myNickName) ViewHolder.ME else ViewHolder.OTHER

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
            dateText.text = Date(message.time).toString()
            nameText.text = message.nickName
            dateText.takeIf { !message.showDate }?.visibility = View.GONE
            message.time.milliseconds.toComponents { _, hours, minutes, seconds, _ ->
                timeText.text = "$hours:$minutes:$seconds"
            }
        }

        companion object {
            const val OTHER = 0
            const val ME = 1
        }

    }
}