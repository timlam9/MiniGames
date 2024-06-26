package home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import games.deal.DealGameEngine
import games.deal.screens.DealScreen
import games.hangman.HangmanGameEngine
import games.hangman.screens.HangmanScreen
import games.memo.MemoGameEngine
import games.memo.screens.MemoGameScreen
import ui.navigation.GameInfoScreen
import ui.navigation.GameScreen

@Composable
fun MainScreen(
    memoGameEngine: MemoGameEngine,
    dealGameEngine: DealGameEngine,
    hangmanGameEngine: HangmanGameEngine,
) {
    var gameInfoScreen: GameInfoScreen by remember { mutableStateOf(GameInfoScreen.None) }
    var screen: GameScreen by remember { mutableStateOf(GameScreen.HOME) }

    Scaffold(
        topBar = {
            if (screen != GameScreen.HOME) {
                Toolbar(
                    title = screen.name,
                    onBackClick = {
                        memoGameEngine.resetGame()
                        screen = GameScreen.HOME
                        gameInfoScreen = GameInfoScreen.None
                    }
                )
            }
        },
    ) {
        when (gameInfoScreen) {
            is GameInfoScreen.Memo -> GameInfoScreen(
                title = (gameInfoScreen as GameInfoScreen.Memo).title,
                content = (gameInfoScreen as GameInfoScreen.Memo).content,
                image = (gameInfoScreen as GameInfoScreen.Memo).image,
                onBackClick = { gameInfoScreen = GameInfoScreen.None },
                gameEngine = memoGameEngine,
            )

            is GameInfoScreen.Deal -> GameInfoScreen(
                title = (gameInfoScreen as GameInfoScreen.Deal).title,
                content = (gameInfoScreen as GameInfoScreen.Deal).content,
                image = (gameInfoScreen as GameInfoScreen.Deal).image,
                onBackClick = { gameInfoScreen = GameInfoScreen.None },
                gameEngine = dealGameEngine,
            )

            GameInfoScreen.None -> {
                GamesNavigation(
                    screen = screen,
                    memoGameEngine = memoGameEngine,
                    dealGameEngine = dealGameEngine,
                    hangmanGameEngine = hangmanGameEngine,
                    onGameCardClick = { screen = it },
                    onInfoIconClick = { gameInfoScreen = it },
                )
            }
        }
    }
}

@Composable
private fun GamesNavigation(
    screen: GameScreen,
    memoGameEngine: MemoGameEngine,
    dealGameEngine: DealGameEngine,
    hangmanGameEngine: HangmanGameEngine,
    onGameCardClick: (GameScreen) -> Unit,
    onInfoIconClick: (GameInfoScreen) -> Unit,
) {
    val memostate by memoGameEngine.state.collectAsState()
    val dealState by dealGameEngine.state.collectAsState()
    val hangmanState by hangmanGameEngine.state.collectAsState()

    when (screen) {
        GameScreen.HOME -> {
            val games = GameScreen.values().toList().filterValidMenuItems()

            MenuScreen(
                games = games,
                onGameCardClick = onGameCardClick,
                onInfoIconClick = { onInfoIconClick(it.toGameInfoScreen()) },
            )
        }

        GameScreen.MEMO -> MemoGameScreen(
            state = memostate,
            onGameStart = memoGameEngine::onGameStart,
            onCardClick = memoGameEngine::onCardClick,
        )

        GameScreen.DEAL -> DealScreen(
            state = dealState,
            onItemClick = dealGameEngine::onItemClick,
        )

        GameScreen.HANGMAN -> HangmanScreen(
            state = hangmanState,
            onAction = hangmanGameEngine::onKeyClick,
            onPlayAgainClick = hangmanGameEngine::onPlayAgainClick,
        )
    }
}

@Composable
private fun Toolbar(
    title: String,
    onBackClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().background(Color.DarkGray).padding(0.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

private fun GameScreen.toGameInfoScreen() = when (this) {
    GameScreen.HOME -> GameInfoScreen.None
    GameScreen.MEMO -> GameInfoScreen.Memo()
    GameScreen.DEAL -> GameInfoScreen.Deal()
    GameScreen.HANGMAN -> GameInfoScreen.None
}

private fun List<GameScreen>.filterValidMenuItems(): List<GameScreen> = filterNot {
    it == GameScreen.HOME
}
