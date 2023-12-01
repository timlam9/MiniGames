import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import domain.MemoGameEngine
import ui.screens.MainScreen

@Composable
@Preview
fun App() {
    val memoGameEngine = MemoGameEngine()

    MaterialTheme {
        MainScreen(memoGameEngine = memoGameEngine)
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
