package the.end2024.carrotclone.data.Repository

import the.end2024.carrotclone.data.model.chatMessage
import the.end2024.carrotclone.network.ApiService

class ChatRepositoryImpl(private val apiService: ApiService) : ChatRepository {
    override suspend fun getChat(): List<chatMessage> {
        val response = apiService.getChat()

        if (response.isSuccessful) {
            val chatMessage = response.body() // Response에서 body를 추출
            return listOfNotNull(chatMessage) // chatMessage가 null이 아닐 경우 리스트로 반환
        } else {
            // 처리할 로직이 실패했을 때
            // 예외 처리 또는 빈 리스트를 반환하는 등의 로직을 추가할 수 있음
            return emptyList()
        }
    }

    override suspend fun sendChat(chat: chatMessage) {
        // ApiService를 사용하여 채팅 데이터를 전송하는 로직을 작성
    }
}