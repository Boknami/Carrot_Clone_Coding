package the.end2024.carrotclone.data.model


data class chatMessage (
    val key : Int,
    val isUser : Boolean,
    val chatMsgTime: String,
    val message : String,
)