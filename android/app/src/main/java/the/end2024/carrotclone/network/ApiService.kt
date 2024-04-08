package the.end2024.carrotclone.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import the.end2024.carrotclone.data.model.Post
import the.end2024.carrotclone.data.model.chatMessage

interface ApiService {
    @GET("posts/1")
    suspend fun getChat(): Response<chatMessage>

    @GET("posts/{number}")
    suspend fun getPostNumber(
        @Path("number") number: Int
    ): Response<Post>
}

