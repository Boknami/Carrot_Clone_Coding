package the.end2024.carrotclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import the.end2024.carrotclone.presentation.nav.NavigationManager
import the.end2024.carrotclone.presentation.theme.CarrotCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarrotCloneTheme {
                //ExScreen(koinViewModel()).Create()
                NavigationManager.addNavigation()
            }
        }
    }
}