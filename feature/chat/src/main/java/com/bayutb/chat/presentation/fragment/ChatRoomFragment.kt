package com.bayutb.chat.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.bayutb.chat.databinding.FragmentChatRoomBinding
import com.bayutb.chat.di.getChatComponent
import com.bayutb.chat.presentation.viewmodel.ChatListViewModel

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */

class ChatRoomFragment : Fragment() {
    private var chatId: Int? = null
    private lateinit var binding: FragmentChatRoomBinding

    private lateinit var viewModel: ChatListViewModel
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
        chatId = arguments?.getInt("chatId")
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