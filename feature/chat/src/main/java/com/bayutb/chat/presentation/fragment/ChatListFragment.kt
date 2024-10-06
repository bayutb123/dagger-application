package com.bayutb.chat.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayutb.chat.databinding.FragmentChatListBinding
import com.bayutb.chat.di.ChatComponent
import com.bayutb.chat.di.getChatComponent
import com.bayutb.chat.domain.model.Chat
import com.bayutb.chat.presentation.adapter.ChatListAdapter
import com.bayutb.chat.presentation.viewmodel.ChatListViewModel
import com.bayutb.core.app.AppRouter
import com.bayutb.core.app.ComponentProvider
import com.bayutb.core.app.Feature
import com.bayutb.core.app.getParentNavBackStackEntry

class ChatListFragment : Fragment(), ChatListAdapter.OnClickListener {
    private lateinit var chatListAdapter: ChatListAdapter

    private val chatComponent by lazy {
        val viewModel: ComponentProvider<ChatComponent> by viewModels(
            ownerProducer = { getParentNavBackStackEntry() },
            factoryProducer = {
                ComponentProvider.ComponentProviderFactory(
                    requireActivity().application.getChatComponent()
                )
            }
        )
        viewModel.component
    }
    private val viewModel: ChatListViewModel by viewModels<ChatListViewModel>(
        ownerProducer = { getParentNavBackStackEntry() },
        factoryProducer = { ChatListViewModel.Factory },
        extrasProducer = {
            MutableCreationExtras().apply {
                set(
                    ChatListViewModel.C_REPOSITORY_KEY,
                    chatComponent.provideChatRepository()
                )
            }
        }
    )
    private lateinit var binding: FragmentChatListBinding

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
        AppRouter.go(
            findNavController(),
            Feature.CHATROOM(chat.id)
        )
    }
}