package the.end2024.carrotclone.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import the.end2024.carrotclone.R
import the.end2024.carrotclone.core.BaseScreen
import the.end2024.carrotclone.data.model.ChatItem
import the.end2024.carrotclone.presentation.contract.ChatListContract
import the.end2024.carrotclone.presentation.nav.NavigationManager
import the.end2024.carrotclone.presentation.theme.gMarket_medium
import the.end2024.carrotclone.presentation.viewModel.ChatListViewModel


class ChattingListScreen (private val viewModel: ChatListViewModel, private val chatViewModel: ChatListViewModel) : BaseScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Create() {
        Effect()

        val state = viewModel.state.collectAsStateWithLifecycle()
        val chatList = state.value.chatList

        Scaffold(
            topBar = { MyTopBar("채팅", false) },
            bottomBar = {MyBottomBar(viewModel) }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn {
                    items(chatList) { chatItem ->
                        Divider(color = Color.LightGray, thickness = 0.5.dp)
                        ChatCard(chatItem)
                    }
                }
            }
        }
    }

    @Composable
    override fun Effect() {
        val context = LocalContext.current
        LaunchedEffect(viewModel.effect) {
            viewModel.effect.onEach { effect ->
                when(effect) {
                    is ChatListContract.Effect.LoadedChatList -> {

                    }
                }
            }.collect()
        }
    }
}

@Composable
fun ChatCard(chatInfo: ChatItem) {
    Card (
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier.clickable{
            NavigationManager.navController.navigate("chat")
        }
    ){
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // 상품 이미지
            Image(
                painter = painterResource(id = R.drawable.icon_carrot),
                contentDescription = null,
                modifier = Modifier.size(60.dp),
            )

            Spacer(modifier = Modifier.width(16.dp))

            // 상품 정보
            Column {
                Row(){
                    Text(text = chatInfo.userName,fontSize = 16.sp, fontFamily = gMarket_medium, color = Color.Black)
                    Text(text = chatInfo.lastDay,fontSize = 12.sp, fontFamily = gMarket_medium, color = Color.Gray)
                }
                Text(text = chatInfo.lastMessage)
            }

            // Spacer를 통해 상품 정보와 아이콘 사이의 간격 조정
            Spacer(modifier = Modifier.weight(1f))

            // 오른쪽 끝에 아이콘
            IconButton(
                onClick = { /* 아이콘 클릭 시 동작 */ },
                modifier = Modifier.align(Alignment.Bottom)
            ) {
                Icon(Icons.Filled.MailOutline, contentDescription = null)
            }
        }
    }
}