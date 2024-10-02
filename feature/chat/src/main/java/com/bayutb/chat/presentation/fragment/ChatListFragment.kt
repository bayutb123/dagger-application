package com.bayutb.chat.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayutb.chat.databinding.FragmentChatListBinding
import com.bayutb.chat.di.getChatComponent
import com.bayutb.chat.domain.model.Chat
import com.bayutb.chat.presentation.adapter.ChatListAdapter
import com.bayutb.chat.presentation.viewmodel.ChatListViewModel
import com.bayutb.core.app.AppRouter
import com.bayutb.core.app.Feature
import com.bayutb.core.app.navController

class ChatListFragment : Fragment(), ChatListAdapter.OnClickListener {
    private lateinit var chatListAdapter: ChatListAdapter

    private lateinit var viewModel: ChatListViewModel
    private lateinit var binding: FragmentChatListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val chatComponent = requireActivity().application.getChatComponent()

        viewModel = ViewModelProvider.create(
            owner = this,
            factory = ChatListViewModel.Factory,
            extras = MutableCreationExtras().apply {
                set(
                    ChatListViewModel.C_REPOSITORY_KEY,
                    chatComponent.provideChatRepository()
                )
            }
        )[ChatListViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatListAdapter = ChatListAdapter(this)
        binding.rvChatList.layoutManager = LinearLayoutManager(context)
        binding.rvChatList.adapter = chatListAdapter

        observe()
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