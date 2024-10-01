package com.bayutb.chat.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bayutb.chat.databinding.FragmentChatRoomBinding
import com.bayutb.chat.di.DaggerChatComponent
import com.bayutb.chat.presentation.viewmodel.ChatListViewModel
import com.bayutb.chat.presentation.viewmodel.ChatListViewModelFactory
import com.bayutb.core.di.getComponent
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */

class ChatRoomFragment : Fragment() {
    private var chatId: Int? = null
    private lateinit var binding: FragmentChatRoomBinding

    @Inject
    lateinit var viewModelFactory: ChatListViewModelFactory
    private lateinit var viewModel: ChatListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerChatComponent.builder()
            .appComponent(requireActivity().application.getComponent())
            .build().inject(this)
        chatId = arguments?.getInt("chatId")
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
}