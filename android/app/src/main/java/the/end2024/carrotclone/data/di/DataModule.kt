package the.end2024.carrotclone.data.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import the.end2024.carrotclone.data.Repository.ChatRepository
import the.end2024.carrotclone.data.Repository.ChatRepositoryImpl
import the.end2024.carrotclone.network.ApiService

// Retrofit 인스턴스 생성
private val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

//애플리케이션의 비즈니스 로직
//데이터를 가져오고 저장하는 등의 기능을 수행하는 클래스들을 정의
private val repositoryModule = module {
    single<ChatRepository> { ChatRepositoryImpl(get()) }
}

private val dataSourceModule = module {
    //single<UserDataSource> { UserLocalDataSource() }
}

val dataModule = apiModule + repositoryModule + dataSourceModule