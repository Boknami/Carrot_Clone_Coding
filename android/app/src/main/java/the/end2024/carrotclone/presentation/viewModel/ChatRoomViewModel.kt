package the.end2024.carrotclone.presentation.viewModel

import android.util.Log
import the.end2024.carrotclone.core.BaseViewModel
import the.end2024.carrotclone.core.UiEvent
import the.end2024.carrotclone.data.model.chatMessage
import the.end2024.carrotclone.presentation.contract.ChatRoomContract

class ChatRoomViewModel : BaseViewModel<ChatRoomContract.State, ChatRoomContract.Event, ChatRoomContract.Effect>() {
    override fun createState(): ChatRoomContract.State {
        return ChatRoomContract.State(
            chatRoomId = "",
            messages = listOf(
                chatMessage(
                    1,true,"안녕하세요?", "오후 2:56"
                ),
        chatMessage(
            2,false,"안녕하세요?", "오후 2:56"
        ),
        chatMessage(
            3,true,"네고 좀요 ㅎㅎ;", "오후 2:56"
        ),
        chatMessage(
            4,true,"네고 좀요 ㅎㅎ;", "오후 2:56"
        ),
        chatMessage(
            5,false,"ㄴㄴ", "오후 2:56"
        ),
        chatMessage(
            6,true,"네고 좀요 ㅎㅎ;", "오후 2:56"
        ),
        chatMessage(
            7,true,"그냥 좀 해주세요 그냥 좀 해주세요 그냥 좀 해주세요 그냥 좀 해주세요 ", "오후 2:56"
        ),
        chatMessage(
            8,false,"ㄴㄴ", "오후 2:56"
        ),
        chatMessage(
            9,true,"네고 좀요 ㅎㅎ;", "오후 2:56"
        ),
        chatMessage(
            10,false,"ㄴㄴ", "오후 2:56"
        ),
        chatMessage(
            11,true,"네고 좀요 ㅎㅎ;", "오후 2:56"
        ),
        chatMessage(
            12,true,"네고 좀요 ㅎㅎ;", "오후 2:56"
        ),
        chatMessage(
            13,false,"ㄴㄴ", "오후 2:56"
        ),
        chatMessage(
            14,true,"네고 좀요 ㅎㅎ;", "오후 2:56"
        ),
        )
        )
    }

    override fun handleEvent(event: UiEvent) {
        when (event) {
            is ChatRoomContract.Event.LoadChatRoom -> {
                // 채팅방 정보를 가져오는 비즈니스 로직을 수행하고 결과를 이펙트로 전달합니다.
                val chatRoomId = event.chatRoomId
                // 실제로는 데이터를 가져오는 로직을 구현해야 합니다.
                sendEffect {
                    ChatRoomContract.Effect.LoadedMessages(chatRoomId)
                }
            }

            is ChatRoomContract.Event.SendMessage -> {
                Log.d("이벤-사이즈", "${state.value.messages.size}")
                //아래 직접 넣은 값 대신 넘겨준 값을 활용해서..
                val newMessage = event.message

                // 변경된 채팅 목록을 UI에 업데이트합니다.
                setState {
                    copy(messages = messages + newMessage)

                }

                // 새로운 메시지가 생겼음을 이펙트로 전달합니다.
                sendEffect{ChatRoomContract.Effect.MessageSent(newMessage)}
            }

            is ChatRoomContract.Event.SendPhoto -> {
                // Handle sending photo logic...
                //sendEffect(ChatRoomContract.Effect.PhotoSent(event.photoUri))
            }
        }
    }
}
