package com.bayutb.chat.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bayutb.chat.databinding.FragmentChatRoomBinding
import com.bayutb.chat.presentation.ChatListActivity
import com.bayutb.chat.presentation.viewmodel.ChatListViewModel
import com.bayutb.chat.presentation.viewmodel.ChatListViewModelFactory
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [ChatRoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val CHAT_ID = "param1"

class ChatRoomFragment : Fragment() {
    private lateinit var binding: FragmentChatRoomBinding
    private var chatId: Int? = null

    @Inject
    lateinit var viewModelFactory: ChatListViewModelFactory
    private lateinit var viewModel: ChatListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as ChatListActivity).chatComponent.inject(this)
        arguments?.let {
            chatId = it.getInt(CHAT_ID)
        }
        viewModel = ViewModelProvider(this, viewModelFactory)[ChatListViewModel::class.java]
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

    companion object {
        @JvmStatic
        fun newInstance(chatId: Int) =
            ChatRoomFragment().apply {
                arguments = Bundle().apply {
                    putInt(CHAT_ID, chatId)
                }
            }
    }
}