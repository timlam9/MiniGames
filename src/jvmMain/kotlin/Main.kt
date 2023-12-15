import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import games.DealGameEngine
import games.MemoGameEngine
import kotlinx.coroutines.delay
import ui.screens.MainScreen

private var keyEventId by mutableStateOf<Int?>(null)

@Composable
@Preview
fun App(
    keyEventId: Int?,
    onKeyEventHandled: () -> Unit,
    memoGameEngine: MemoGameEngine,
    dealGameEngine: DealGameEngine,
) {
    LaunchedEffect(keyEventId) {
        keyEventId?.let { dealGameEngine.onItemClick(it) }
        delay(300)
        onKeyEventHandled()
    }
    MaterialTheme {
        MainScreen(
            memoGameEngine = memoGameEngine,
            dealGameEngine = dealGameEngine,
        )
    }
}

fun main() = application {
    val memoGameEngine = MemoGameEngine()
    val dealGameEngine = DealGameEngine()
    Window(
        onKeyEvent = { keyEvent ->
            return@Window onKeyEvent(keyEvent)
        },
        onCloseRequest = ::exitApplication,
    ) {
        App(
            keyEventId = keyEventId,
            onKeyEventHandled = { keyEventId = null },
            memoGameEngine = memoGameEngine,
            dealGameEngine = dealGameEngine,
        )
    }
}

private fun onKeyEvent(keyEvent: KeyEvent): Boolean {
    keyEventId = when (keyEvent.awtEventOrNull?.keyChar) {
        '1' -> 0
        '2' -> 1
        '3' -> 2
        '4' -> 3
        '5' -> 4
        '6' -> 5
        else -> null
    }
    return true
}
