package com.bayutb.chat.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bayutb.chat.R
import com.bayutb.chat.databinding.ActivityChatListBinding
import com.bayutb.chat.di.ChatComponent
import com.bayutb.chat.di.DaggerChatComponent
import com.bayutb.chat.presentation.fragment.ChatListFragment
import com.bayutb.chat.presentation.fragment.ChatRoomFragment
import com.bayutb.core.di.getComponent

class ChatListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatListBinding
    lateinit var chatComponent: ChatComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatComponent = DaggerChatComponent.builder()
            .appComponent(application.getComponent())
            .build()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, ChatListFragment())
                .commit()
        }
    }

    fun goToDetailFragment(chatId: Int) {
        val fragment = ChatRoomFragment.newInstance(chatId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}