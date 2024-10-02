package com.bayutb.chat.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.bayutb.chat.databinding.FragmentChatRoomBinding
import com.bayutb.chat.di.ChatComponent
import com.bayutb.chat.di.getChatComponent
import com.bayutb.chat.presentation.viewmodel.ChatListViewModel
import com.bayutb.core.app.ComponentProvider
import com.bayutb.core.app.getParentNavBackStackEntry

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */

class ChatRoomFragment : Fragment() {
    private var chatId: Int? = null
    private lateinit var binding: FragmentChatRoomBinding
    private val chatComponent by lazy {
        val viewModel: ComponentProvider<ChatComponent> by viewModels (
            ownerProducer = { getParentNavBackStackEntry() },
            factoryProducer = {
                ComponentProvider.ComponentProviderFactory(
                    requireActivity().application.getChatComponent()
                )
            }
        )
        viewModel.component
    }
    private val viewModel by viewModels<ChatListViewModel>(
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chatId = arguments?.getInt("chatId")

        Log.d("Dagger", "$chatComponent CHAT ROOM")
        Log.d("Dagger", "$viewModel CHAT ROOM")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatRoomBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatId?.let { viewModel.getChatDetail(it) }
        observe()
    }

    private fun observe() {
        viewModel.chatDetail.observe(viewLifecycleOwner) { chat ->
            binding.sender.text = chat.sender
            binding.msg.text = chat.msg
        }
    }
}