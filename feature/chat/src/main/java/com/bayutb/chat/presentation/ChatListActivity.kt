package com.bayutb.chat.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bayutb.chat.R
import com.bayutb.chat.di.DaggerChatComponent
import com.bayutb.mydaggerapplication.getComponent
import retrofit2.Retrofit
import javax.inject.Inject

class ChatListActivity : AppCompatActivity() {
    @Inject
    lateinit var retrofit: Retrofit
    @Inject
    lateinit var viewModel: ChatListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        DaggerChatComponent.builder()
            .appComponent(application.getComponent())
            .build().inject(this)

        observe()
        Log.d("Dagger", retrofit.toString())
    }

    private fun observe() {
        viewModel.chats.observe(this) { chatList ->
            chatList.forEach { chat ->
                Log.d("Dagger", "sender: ${chat.sender} - ${chat.msg}")
            }
        }
    }
}