package the.end2024.carrotclone.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import the.end2024.carrotclone.R
import the.end2024.carrotclone.network.ApiService
import the.end2024.carrotclone.presentation.nav.NavigationManager.navController
import the.end2024.carrotclone.presentation.theme.CarrotCloneTheme
import the.end2024.carrotclone.presentation.theme.gMarket_bold
import the.end2024.carrotclone.presentation.theme.gMarket_light
import the.end2024.carrotclone.presentation.theme.gMarket_medium

class ApiClient : KoinComponent {

    // AuthApi 의존성 주입
    private val authApi: ApiService by inject()

    fun fetchPost1() {
        Log.d("테스트", "1")
        // 레트로핏 요청 실행
        runBlocking {
            Log.d("테스트", "2")
            val response = authApi.getPostNumber(1)
            Log.d("테스트", "3")
            if (response.isSuccessful) {
                val post = response.body()
                post?.let {
                    // 성공적으로 데이터를 받아온 경우 처리
                    Log.d("테스트", "UserId: ${it.userId}, Title: ${it.title}, Body: ${it.body}")
                }
            } else {
                // 요청 실패 시 처리
                Log.d("테스트", "Request failed with code: ${response.code()}")
            }
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun screen() {
    val apiClient = remember { ApiClient() }
    apiClient.fetchPost1()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        mainBanner()
        //startImage()
        explainText()


        Button(
            onClick = {
                navController.navigate("firstUser")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF03C75A),
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) { // 아이콘과 텍스트를 수직으로 정렬
                Image(
                    painter = painterResource(id = R.drawable.ic_naver2),
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp).size(24.dp),
                )

                Text(
                    text = "네이버로 시작하기",
                    fontSize = 16.sp,
                    fontFamily = gMarket_medium,
                    color = Color.White
                )
            }
        }

        // 간격을 조절하기 위한 Spacer 추가
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("firstUser")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF7E600),
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_kakao), // 네이버 아이콘 리소스 ID로 변경
                contentDescription = "네이버 아이콘", // 아이콘에 대한 설명 추가
                modifier = Modifier.padding(end = 8.dp).size(24.dp), // 아이콘과 텍스트 사이 간격 추가
                tint = Color.Black
            )

            Text(
                text = "카카오로 시작하기",
                fontSize = 16.sp,
                fontFamily = gMarket_medium,
                color = Color.Black
            )
        }
    }
}

@Composable
fun setMyLoc() {
    Button(
            onClick = {
                navController.navigate("firstUser")
            },
            modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE78111),
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(
            text = "내 동네 설정하고 시작하기",
            fontSize = 16.sp,
            fontFamily = gMarket_light,
            color = Color.White
        )
    }
}

@Composable
fun explainText() {
    Text(
        text = "우리 동네 중고 직거래 당근마켓",
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = gMarket_medium
    )

    Spacer(modifier = Modifier.padding(10.dp))
    Text(
        text = "당근마켓은 동네 직거래 마켓이에요.\n내 동네를 설정하고 시작해보세요!",
        color = Color.Black,
        fontSize = 16.sp,
        fontFamily = gMarket_light
    )

    Spacer(modifier = Modifier.padding(20.dp))
}

@Composable
fun mainBanner() {
    Spacer(modifier = Modifier.padding(20.dp))
    Text(
        text = "당근마켓",
        color = Color(0xFFE78111),
        fontSize = 50.sp,
        fontFamily = gMarket_bold
    )
    Spacer(modifier = Modifier.padding(20.dp))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CarrotCloneTheme {
        screen()
    }
}