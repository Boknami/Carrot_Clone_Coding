package the.end2024.carrotclone.data.Repository

import the.end2024.carrotclone.data.model.chatMessage

interface ChatRepository {
    suspend fun getChat(): List<chatMessage>
    suspend fun sendChat(chat: chatMessage)
}