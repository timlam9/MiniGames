import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import games.deal.DealGameEngine
import games.memo.MemoGameEngine
import kotlinx.coroutines.delay
import games.deal.model.DealItem
import home.MainScreen

private var keyEventId by mutableStateOf<Int?>(null)
private var type by mutableStateOf(DealItem.Type.BLUE)

@Composable
@Preview
fun App(
    keyEventId: Int?,
    onKeyEventHandled: () -> Unit,
    memoGameEngine: MemoGameEngine,
    dealGameEngine: DealGameEngine,
) {
    LaunchedEffect(keyEventId) {
        keyEventId?.let { dealGameEngine.onKeyPressed(it, type) }
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
    val char = keyEvent.awtEventOrNull?.keyChar
    keyEventId = try {
        val id = char?.digitToInt()

        if (keyEvent.isCtrlPressed) {
            type = DealItem.Type.RED
            id?.let { it - 1 }
        } else {
            type = DealItem.Type.BLUE
            id?.let { it - 1 }
        }
    } catch (e: Exception) {
        null
    }
    return true
}
