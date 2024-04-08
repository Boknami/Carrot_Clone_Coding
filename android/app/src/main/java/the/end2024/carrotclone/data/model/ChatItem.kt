package the.end2024.carrotclone.data.model

data class ChatItem(
    val chatId: String,
    val userImage : String,
    val userName : String,
    val lastDay : String,
    val lastMessage: String,
    val unreadCount: Int
)