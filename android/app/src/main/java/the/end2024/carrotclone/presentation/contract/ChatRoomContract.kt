package the.end2024.carrotclone.presentation.contract

import the.end2024.carrotclone.core.UiEffect
import the.end2024.carrotclone.core.UiEvent
import the.end2024.carrotclone.core.UiState
import the.end2024.carrotclone.data.model.chatMessage

class ChatRoomContract {
    data class State(
        val chatRoomId: String,
        val messages: List<chatMessage>
    ) : UiState

    sealed class Event : UiEvent {
        data class LoadChatRoom(val chatRoomId: String) : Event()
        data class SendMessage(val message: chatMessage) : Event()
        data class SendPhoto(val photoUri: String) : Event()
    }

    sealed class Effect : UiEffect {
        //임시로 스트링으로!
        data class LoadedMessages(val chatRoomId: String) : Effect()
        data class MessageSent(val message: chatMessage) : Effect()
        data class PhotoSent(val photoUri: String) : Effect()
    }
}
