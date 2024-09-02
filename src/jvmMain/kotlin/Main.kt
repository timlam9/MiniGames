import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import games.batlleship.BattleshipGameEngine
import games.deal.DealGameEngine
import games.memo.MemoGameEngine
import kotlinx.coroutines.delay
import games.deal.model.DealItem
import games.findTheStar.FindTheStarGameEngine
import games.hangman.HangmanGameEngine
import games.hideAndChess.HideAndChessGameEngine
import games.kim.KimGameEngine
import games.mastermind.MastermindGameEngine
import home.MainScreen

private var keyEventId by mutableStateOf<Int?>(null)
private var type by mutableStateOf(DealItem.Type.BLUE)

fun main() = application {
    val windowState = rememberWindowState(placement = WindowPlacement.Fullscreen)

    val memoGameEngine = MemoGameEngine()
    val dealGameEngine = DealGameEngine()
    val hangmanGameEngine = HangmanGameEngine()
    val hideAndChessGameEngine = HideAndChessGameEngine()
    val findTheStarGameEngine = FindTheStarGameEngine()
    val battleshipGameEngine = BattleshipGameEngine()
    val mastermindGameEngine = MastermindGameEngine()
    val kimGameEngine = KimGameEngine(windowState.size)

    Window(
        onKeyEvent = { keyEvent -> return@Window onKeyEvent(keyEvent) },
        onCloseRequest = ::exitApplication,
        state = windowState,
    ) {
        App(
            keyEventId = keyEventId,
            onKeyEventHandled = { keyEventId = null },
            memoGameEngine = memoGameEngine,
            dealGameEngine = dealGameEngine,
            hangmanGameEngine = hangmanGameEngine,
            hideAndChessGameEngine = hideAndChessGameEngine,
            findTheStarGameEngine = findTheStarGameEngine,
            battleshipGameEngine = battleshipGameEngine,
            mastermindGameEngine = mastermindGameEngine,
            kimGameEngine = kimGameEngine,
        )
    }
}

@Composable
@Preview
fun App(
    keyEventId: Int?,
    onKeyEventHandled: () -> Unit,
    memoGameEngine: MemoGameEngine,
    dealGameEngine: DealGameEngine,
    hangmanGameEngine: HangmanGameEngine,
    hideAndChessGameEngine: HideAndChessGameEngine,
    findTheStarGameEngine: FindTheStarGameEngine,
    battleshipGameEngine: BattleshipGameEngine,
    mastermindGameEngine: MastermindGameEngine,
    kimGameEngine: KimGameEngine,
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
            hangmanGameEngine = hangmanGameEngine,
            hideAndChessGameEngine = hideAndChessGameEngine,
            findTheStarGameEngine = findTheStarGameEngine,
            battleshipGameEngine = battleshipGameEngine,
            mastermindGameEngine = mastermindGameEngine,
            kimGameEngine = kimGameEngine,
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
