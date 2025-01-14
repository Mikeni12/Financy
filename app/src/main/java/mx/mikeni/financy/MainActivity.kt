package mx.mikeni.financy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import mx.mikeni.ui.FinancyTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinancyTheme {
                KoinContext {
                    MainScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}
