package the.end2024.carrotclone.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import the.end2024.carrotclone.R
import the.end2024.carrotclone.core.BaseScreen
import the.end2024.carrotclone.data.model.SoldProduct
import the.end2024.carrotclone.presentation.contract.ChatListContract
import the.end2024.carrotclone.presentation.nav.NavigationManager.navController
import the.end2024.carrotclone.presentation.theme.CarrotCloneTheme
import the.end2024.carrotclone.presentation.theme.gMarket_medium
import the.end2024.carrotclone.presentation.viewModel.ChatListViewModel

class mainPage(private val viewModel: ChatListViewModel) : BaseScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Create() {
        Effect()

        Scaffold(
            topBar = { MyTopBar("역삼동", true) },
            floatingActionButton = { BottomFloatingActionButton() },
            bottomBar = {MyBottomBar(viewModel) }
        ) { paddingValues ->
            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                LazyColumn{
                    items(soldProducts) { product ->
                        Divider(color = Color.LightGray, thickness = 0.5.dp)
                        SoldProductCard(product)
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
                        navController.navigate("chatList")
                    }
                }
            }.collect()
        }
    }
}

@Composable
fun SoldProductCard(product: SoldProduct) {
    Card (
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier.clickable{

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
                modifier = Modifier.size(100.dp),
            )

            Spacer(modifier = Modifier.width(16.dp))

            // 상품 정보
            Column {
                Text(text = product.title)
                Text(text = product.location)
                Text(text = product.day)
                Text(text = "${product.price} 원")
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(titleText: String, isVisible: Boolean){
    TopAppBar(
        title = {
            Text(
                text = titleText,
                fontSize = 20.sp,
                fontFamily = gMarket_medium
            )
        },
        navigationIcon = {
            // 뒤로 가기 버튼 아이콘 추가
            if(false){
                IconButton(onClick = { /* 뒤로 가기 동작 */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = {
            if(isVisible){
                Row() {
                    IconButton(
                        onClick = { /* 검색 아이콘 클릭 시 동작 */ }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }

                    IconButton(onClick = { /* 카테고리 아이콘 클릭 시 동작 */ }) {
                        Icon(imageVector = Icons.Default.Build, contentDescription = "Category")
                    }

                    IconButton(onClick = { /* 알림 아이콘 클릭 시 동작 */ }) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(Color.White)
    )

}

@Composable
fun BottomFloatingActionButton() {
    FloatingActionButton(
        onClick = {

        },
        modifier = Modifier.background(color = Color.White)
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "Menu",
            tint = Color(0xFFE78111)
        )
    }


}

@Composable
fun MyBottomBar(viewModel: ChatListViewModel){

    BottomAppBar(
        containerColor = Color.White,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            //horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { navController.navigate("main") },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.icon_botton_home_on),
                    contentDescription = null,
                    modifier =  Modifier.size(24.dp),
                )

                Text(
                    text = "홈",
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(8.dp)) // 공간 추가

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        // 채팅 목록 불러오기
                        viewModel.handleEvent(ChatListContract.Event.LoadChatList)

                        // 채팅 화면으로 이동
                        //navController.navigate("chat")
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_botton_msg_off),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )

                Text(
                    text = "채팅",
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(8.dp)) // 공간 추가

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_botton_user_off),
                    contentDescription = null,
                    modifier =  Modifier.size(24.dp),
                )

                Text(
                    text = "나의 당근",
                    color = Color.Black
                )
            }
        }
    }
}


//임시 테스트를 위한..
val soldProducts  = listOf(
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "의자팜",
        "서울",
        "2일 전",
        30000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "책상마켓",
        "부산",
        "3일 전",
        45000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "소파시장",
        "대구",
        "5일 전",
        120000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "침대마켓",
        "인천",
        "1일 전",
        80000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "식탁마트",
        "광주",
        "4일 전",
        60000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "의자팜",
        "서울",
        "2일 전",
        30000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "책상마켓",
        "부산",
        "3일 전",
        45000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "소파시장",
        "대구",
        "5일 전",
        120000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "침대마켓",
        "인천",
        "1일 전",
        80000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "식탁마트",
        "광주",
        "4일 전",
        60000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "의자팜",
        "서울",
        "2일 전",
        30000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "책상마켓",
        "부산",
        "3일 전",
        45000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "소파시장",
        "대구",
        "5일 전",
        120000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "침대마켓",
        "인천",
        "1일 전",
        80000
    ),
    SoldProduct(
        "id =R.drawable.icon_carrot",
        "식탁마트",
        "광주",
        "4일 전",
        60000
    )
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    CarrotCloneTheme {

    }
}