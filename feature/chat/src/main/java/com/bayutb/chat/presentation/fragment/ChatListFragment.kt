package com.bayutb.chat.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayutb.chat.databinding.FragmentChatListBinding
import com.bayutb.chat.di.ChatComponent
import com.bayutb.chat.di.DaggerChatComponent
import com.bayutb.chat.domain.model.Chat
import com.bayutb.chat.presentation.adapter.ChatListAdapter
import com.bayutb.chat.presentation.viewmodel.ChatListViewModel
import com.bayutb.chat.presentation.viewmodel.ChatListViewModelFactory
import com.bayutb.core.app.AppRouter
import com.bayutb.core.app.Feature
import com.bayutb.core.app.NavControllerProvider
import com.bayutb.core.app.navController
import com.bayutb.core.di.getComponent
import com.bayutb.mydaggerapplication.MainActivity
import javax.inject.Inject

class ChatListFragment : Fragment(), ChatListAdapter.OnClickListener {
    private lateinit var chatListAdapter: ChatListAdapter
    @Inject
    lateinit var viewModelFactory: ChatListViewModelFactory
    private lateinit var viewModel: ChatListViewModel
    private lateinit var binding: FragmentChatListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerChatComponent.builder()
            .appComponent(requireActivity().application.getComponent())
            .build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatListBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, viewModelFactory)[ChatListViewModel::class.java]

        chatListAdapter = ChatListAdapter(this)
        binding.rvChatList.layoutManager = LinearLayoutManager(context)
        binding.rvChatList.adapter = chatListAdapter

        observe()

        return binding.root
    }

    private fun observe() {
        viewModel.chats.observe(viewLifecycleOwner) { chatList ->
            chatListAdapter.setData(chatList)
        }
    }

    override fun onClick(chat: Chat) {
        val bundle = Bundle().also {
            it.putInt("chatId", chat.id)
        }
        AppRouter.go(requireActivity().navController(), Feature.CHATROOM, bundle)
    }
}