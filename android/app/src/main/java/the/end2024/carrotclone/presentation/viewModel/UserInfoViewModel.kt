package the.end2024.carrotclone.presentation.viewModel

import the.end2024.carrotclone.core.BaseViewModel
import the.end2024.carrotclone.core.UiEffect
import the.end2024.carrotclone.core.UiEvent
import the.end2024.carrotclone.core.UiState
import the.end2024.carrotclone.presentation.contract.UserInfoContract
import the.end2024.carrotclone.ex.ExContract
import the.end2024.carrotclone.presentation.nav.NavigationManager

class UserInfoViewModel : BaseViewModel<UserInfoContract.State, UserInfoContract.Event, UserInfoContract.Effect>() {
    override fun createState(): UserInfoContract.State {
        return UserInfoContract.State(id = "", password = "")
    }

    override fun handleEvent(event: UiEvent) {
        when (event) {
            is UserInfoContract.Event.UpdateId -> {
                setState { copy(id = event.id) }
            }
            is UserInfoContract.Event.UpdatePassword -> {
                setState { copy(password = event.password) }
            }
            is UserInfoContract.Event.PerformSignUp -> {
                // 서버에 회원가입 요청 -> 처리 결과에 따라 적절한 이펙트를 보냅니다.
                sendEffect { UserInfoContract.Effect.SignUpSuccess }
            }
            is UserInfoContract.Event.PerformLogin -> {
                if (state.value.id == "123" && state.value.password == "123") {
                    sendEffect {
                        val message = "정상적인 로그인입니다."
                        UserInfoContract.Effect.LoginSuccess(message)
                    }
                    NavigationManager.navController.navigate("main")
                }
                else{
                    sendEffect {
                        val message = "존재하는 아이디나 비밀번호가 없습니다."
                        UserInfoContract.Effect.LoginSuccess(message)
                    }
                }
            }
        }
    }
}
