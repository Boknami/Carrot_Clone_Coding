package the.end2024.carrotclone.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import the.end2024.carrotclone.presentation.ui.ChattingListScreen
import the.end2024.carrotclone.presentation.ui.ChattingScreen
import the.end2024.carrotclone.presentation.ui.loginPage
import the.end2024.carrotclone.presentation.ui.mainPage
import the.end2024.carrotclone.presentation.ui.screen
import the.end2024.carrotclone.presentation.ui.searchLocation
import the.end2024.carrotclone.presentation.ui.signUpPage

object NavigationManager {
    lateinit var navController: NavController

    @Composable
    fun addNavigation(){
        val navController = rememberNavController()
        NavigationManager.navController = navController
        NavHost(
            navController = navController,
            startDestination = "start"
        ) {
            composable("start") {
                screen()
            }
            composable("firstUser") {
                searchLocation()
            }
            composable("login") {
                loginPage(koinViewModel()).Create()
            }
            composable("signUp") {
                signUpPage(koinViewModel()).Create()
            }
            composable("main") {
                mainPage(koinViewModel()).Create()
            }
            composable("chatList") {
                ChattingListScreen(koinViewModel(), koinViewModel()).Create()
            }
            composable("chat") {
                ChattingScreen(koinViewModel()).Create()
            }
        }
    }
}