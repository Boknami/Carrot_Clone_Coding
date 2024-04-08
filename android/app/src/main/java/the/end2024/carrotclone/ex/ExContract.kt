package the.end2024.carrotclone.ex

import the.end2024.carrotclone.core.UiEffect
import the.end2024.carrotclone.core.UiEvent
import the.end2024.carrotclone.core.UiState

// MVI 패턴에서 뷰모델이 State를 가지고 있고 뷰에서 Intent를 보내서 State를 변경한다.
// 그 때, Intent에서 새로운 SideEffect가 발생할 수 있다.
// 그래서 ViewModel마다 State, Intent, SideEffect를 다룰 수 있어야 하는데 이를 Contract라는 클래스에서 한번에 관리
// 모듈화라기 보다는 모든 뷰모델은 하나의 state를 바꾸더라도 intent를 통해서 바꿔야하는데
// 이를 각각 설정하려면 너무 비효율적이다. 관리가 잘되는 장점과 반대로 textField의 text 값과 같은 것도 정말 단순하지만 intent를 통해 바꿔야하는 불편함이 있다.
// 이를 조금 더 수월하게 하고자 baseViewModel을 만들었고 공통적으로 state를 가지고 있고, intent를 보내고 이를 받는 기본적인 것들을 baseViewModel에서 미리 선언해놓기 위해서
// interface인 UiState, UiEvent, UiEffect를 가지고 통신 및 처리 하는 코드를 만들어 놨다고 하면 될듯

class ExContract {
    data class State(
        val count: Int
    ) : UiState

    sealed class Event : UiEvent {
        object IncreaseCount : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }
}