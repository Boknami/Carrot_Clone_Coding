package the.end2024.carrotclone.data.di

import org.koin.dsl.module
import the.end2024.carrotclone.presentation.viewModel.ChatListViewModel
import the.end2024.carrotclone.presentation.viewModel.ChatRoomViewModel
import the.end2024.carrotclone.presentation.viewModel.UserInfoViewModel

private val viewModelModule = module {
    //single { ExViewModel() }
    single { UserInfoViewModel() }
    single { ChatListViewModel() }
    single { ChatRoomViewModel() }

}

val viewModule = viewModelModule