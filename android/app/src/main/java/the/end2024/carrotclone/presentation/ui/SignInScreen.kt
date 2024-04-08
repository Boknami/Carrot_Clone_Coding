package the.end2024.carrotclone.presentation.ui

import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import the.end2024.carrotclone.core.BaseScreen
import the.end2024.carrotclone.presentation.contract.UserInfoContract
import the.end2024.carrotclone.presentation.theme.CarrotCloneTheme
import the.end2024.carrotclone.presentation.theme.gMarket_medium
import the.end2024.carrotclone.presentation.viewModel.UserInfoViewModel


class loginPage(private val viewModel: UserInfoViewModel) : BaseScreen() {
    @Composable
    override fun Create() {
        Effect()

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                upBar()

                Text(text = "")
            }
        }
    }

    @Composable
    override fun Effect() {
        val context = LocalContext.current
        LaunchedEffect(viewModel.effect) {
            viewModel.effect.onEach { effect ->
                when(effect) {
                    is UserInfoContract.Effect.LoginSuccess -> {
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }
                    is UserInfoContract.Effect.SignUpSuccess-> {

                    }
                }
            }.collect()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun upBar() {
    var mylocation by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
            IconButton(
                onClick = {
                    backDispatcher?.onBackPressed()
                },

                ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.width(60.dp))

            Text(
                text = "로그인/가입",
                fontFamily = gMarket_medium,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    CarrotCloneTheme {
    }
}