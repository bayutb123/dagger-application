package com.bayutb.chat.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bayutb.chat.R
import com.bayutb.chat.domain.model.Chat

class ChatListAdapter(
    private val listener: OnClickListener
) : RecyclerView.Adapter<ChatListAdapter.ChatViewHolder>() {
    private var chatList: List<Chat> = emptyList()
    fun setData(data: List<Chat>) {
        chatList = data
        notifyDataSetChanged()
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderTextView: TextView = itemView.findViewById(R.id.senderTextView)
        val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
        val container: View = itemView.rootView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.senderTextView.text = chat.sender
        holder.messageTextView.text = chat.msg
        holder.container.setOnClickListener {
            listener.onClick(chat)
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    interface OnClickListener {
        fun onClick(chat: Chat)
    }
}
