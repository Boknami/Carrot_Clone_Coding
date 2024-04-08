package the.end2024.carrotclone.presentation.contract

import the.end2024.carrotclone.core.UiEffect
import the.end2024.carrotclone.core.UiEvent
import the.end2024.carrotclone.core.UiState
import the.end2024.carrotclone.data.model.ChatItem

class ChatListContract {
    data class State(
        val chatList: List<ChatItem>
    ) : UiState

    sealed class Event : UiEvent {
        object LoadChatList : Event()
    }

    sealed class Effect : UiEffect {
        data class LoadedChatList(val chatList: List<ChatItem>) : Effect()
    }
}

