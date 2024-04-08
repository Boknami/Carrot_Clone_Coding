package the.end2024.carrotclone.presentation.contract

import the.end2024.carrotclone.core.UiEffect
import the.end2024.carrotclone.core.UiEvent
import the.end2024.carrotclone.core.UiState
import the.end2024.carrotclone.ex.ExContract

class UserInfoContract {
    data class State(
        val id: String,
        val password: String
    ) : UiState

    sealed class Event : UiEvent {
        data class UpdateId(val id: String) : Event()
        data class UpdatePassword(val password: String) : Event()
        object PerformSignUp : Event()
        object PerformLogin : Event()
    }

    sealed class Effect : UiEffect {
        object SignUpSuccess : Effect()
        data class LoginSuccess(val message: String) : Effect()
    }
}
