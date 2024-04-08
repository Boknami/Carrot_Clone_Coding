package the.end2024.carrotclone.presentation.ui

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import the.end2024.carrotclone.R
import the.end2024.carrotclone.core.BaseScreen
import the.end2024.carrotclone.data.model.chatMessage
import the.end2024.carrotclone.presentation.contract.ChatRoomContract
import the.end2024.carrotclone.presentation.theme.CarrotCloneTheme
import the.end2024.carrotclone.presentation.theme.gMarket_medium
import the.end2024.carrotclone.presentation.viewModel.ChatRoomViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChattingScreen(private val viewModel: ChatRoomViewModel) : BaseScreen() {

    @Composable
    override fun Create() {
        Effect()

        //val cmList = remember { mutableStateListOf(*cmList.toTypedArray()) }
        testUI(viewModel)
    }

    @Composable
    override fun Effect() {
        val context = LocalContext.current
        val listState = rememberLazyListState()

        LaunchedEffect(viewModel.effect) {
            viewModel.effect.onEach { effect ->
                when(effect) {
                    is ChatRoomContract.Effect.LoadedMessages -> {

                    }
                    is ChatRoomContract.Effect.MessageSent -> {

                    }
                    is ChatRoomContract.Effect.PhotoSent -> {

                    }
                }
            }.collect()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun testUI(viewModel: ChatRoomViewModel) {
    //뷰모델 안에 값 가져오기
    val state = viewModel.state.collectAsStateWithLifecycle()
    val messageList = state.value.messages

    val listState = rememberLazyListState()
    LaunchedEffect(key1 = messageList.size) {
        if (messageList.isNotEmpty()) {
            listState.scrollToItem(messageList.size - 1)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(Modifier.fillMaxSize()) {
            //[1] 상단바(뒤로가기, 제목)chatTopBar()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp),
                    //.background(color = Color.Green),
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

                Spacer(modifier = Modifier.weight(1f)) // 텍스트를 가운데로 정렬하기 위한 Spacer

                Text(
                    text = "복 남",
                    fontSize = 24.sp,
                    fontFamily = gMarket_medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterVertically) // 텍스트를 세로 중앙에 정렬
                )

                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.weight(0.5f))
            }
            Divider(color = Color.LightGray, thickness = 0.5.dp)


            //[2] 채팅 목록
            LazyColumn(
                // TopBar 영역과 TextField 영역을 제외한 나머지 공간을 모두 차지하도록
                modifier = Modifier.weight(1f),
                state = listState,
            ) {
                items(
                    items = messageList,
                    key = { (it.key) },
                ) { chatMessage ->
                    ChatBubble(chatMessage)
                }
            }

            //[3] 텍스트 필드 & 전송 버튼
            var chatMsg by remember { mutableStateOf("") } // 채팅 메시지 상태

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {
                        //사진 전송
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = ""
                    )
                }

                TextField(
                    value = chatMsg,
                    onValueChange = { chatMsg = it },
                    Modifier
                        .border(
                            border = BorderStroke(1.dp, Color.LightGray),
                            shape = RoundedCornerShape(24.dp)
                        )
                        .background(Color.LightGray),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = Color.White
                    ),
                )

                IconButton(
                    onClick = {
                        val newMessage = chatMsg
                        if (newMessage.isNotBlank()) {
                            val curTime = LocalDateTime.now()
                            val formatter = DateTimeFormatter.ofPattern("a h:mm")
                            val formattedTime = curTime.format(formatter)

                            val inputMsg  = chatMessage(
                                    key = messageList.size + 1,
                                    isUser = true,
                                    message = newMessage,
                                    chatMsgTime = formattedTime.toString()
                            )
                            viewModel.handleEvent(ChatRoomContract.Event.SendMessage(inputMsg))
                            chatMsg = "" // 메시지 전송 후 채팅 메시지 초기화
                        }
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Composable
fun ChatBubble(chatMessage : chatMessage) {
    // 내가 보낸 채팅 -> 끝에서부터 UI
    // 상대가 보낸 채팅 -> 시작에서부터 UI
    val messageArrangement = if (chatMessage.isUser) Arrangement.End else Arrangement.Start

    // 채팅 아이템의 구성요소들(프로필 사진, 메세지, 시각)을 가로로 배치
    Row(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = messageArrangement,
        verticalAlignment = Alignment.Bottom,
    ) {
        // 내가 보낸 채팅
        if (chatMessage.isUser) {
            Text(text = chatMessage.chatMsgTime)
            Spacer(modifier = Modifier.width(8.dp))
            MessageBox(
                message = chatMessage.message,
                isUser = true,
            )
            // 상대방이 보낸 채팅(프로필 사진을 포함)
        } else {
            Image(
                painter = painterResource(id = R.drawable.test_img),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .align(Alignment.Top)
            )
//            ProfileImage(
//                modifier = Modifier
//                    .align(Alignment.Top)
//                    .size(48.dp),
//            )
            Spacer(modifier = Modifier.width(8.dp))
            MessageBox(
                message = chatMessage.message,
                isUser = false,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = chatMessage.chatMsgTime)
        }
    }
}

@Composable
fun MessageBox(
    modifier: Modifier = Modifier,
    message: String,
    isUser: Boolean,
) {
    // optional 채팅이 길어질 경우 화면의 최대 2/3 를 차지 하도록
    val maxWidthDp = LocalConfiguration.current.screenWidthDp.dp * 2 / 3

    Box(
        modifier = modifier
            .widthIn(max = if (isUser) maxWidthDp else maxWidthDp - 56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isUser) Color(0xFFE78111) else Color.LightGray)
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = message,
            color = if (isUser) Color.White else Color.Black,
            modifier = Modifier.padding(all = 4.dp),
        )
    }
}

@Composable
fun TimeText(time: String) {
    Text(
        text = time,
        color = Color.Black,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    CarrotCloneTheme {
        //testUI()
    }
}
