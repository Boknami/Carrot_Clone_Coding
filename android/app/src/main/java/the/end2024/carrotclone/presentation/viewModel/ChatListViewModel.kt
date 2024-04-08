package the.end2024.carrotclone.presentation.viewModel

import android.util.Log
import the.end2024.carrotclone.core.BaseViewModel
import the.end2024.carrotclone.core.UiEvent
import the.end2024.carrotclone.data.model.ChatItem
import the.end2024.carrotclone.presentation.contract.ChatListContract

class ChatListViewModel : BaseViewModel<ChatListContract.State, ChatListContract.Event, ChatListContract.Effect>() {
    override fun createState(): ChatListContract.State {
        return ChatListContract.State(chatList = emptyList())
    }

    override fun handleEvent(event: UiEvent) {
        when (event) {
            is ChatListContract.Event.LoadChatList -> {
                Log.d("확인", "이벤트실행")
                // 채팅 목록을 가져오는 비즈니스 로직을 수행
                val chatList = listOf(
                    ChatItem("1", "사진 리소스..", "김봉남","1달전","안녕하셔요?", 1),
                    ChatItem("2", "사진 리소스..", "최봉남","1달전","안녕하셔요?", 1),
                    ChatItem("3", "사진 리소스..", "삼봉남","1달전","안녕하셔요?", 1)
                )

                setState { copy(chatList = chatList) }

                //하고 결과를 이펙트로 전달
                sendEffect {
                    ChatListContract.Effect.LoadedChatList(chatList)
                }
            }
        }
    }
}
