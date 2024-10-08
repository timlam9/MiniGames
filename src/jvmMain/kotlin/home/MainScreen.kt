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
import games.batlleship.BattleshipGameEngine
import games.batlleship.screens.BattleshipScreen
import games.deal.DealGameEngine
import games.deal.screens.DealScreen
import games.findTheStar.FindTheStarGameEngine
import games.findTheStar.screens.FindTheStarScreen
import games.hangman.HangmanGameEngine
import games.hangman.screens.HangmanScreen
import games.hideAndChess.HideAndChessGameEngine
import games.hideAndChess.model.levels
import games.hideAndChess.screens.HideAndChessLevelsScreen
import games.hideAndChess.screens.HideAndChessScreen
import games.kim.KimGameEngine
import games.kim.screens.KimGameScreen
import games.mastermind.MastermindGameEngine
import games.mastermind.screens.MastermindScreen
import games.memo.MemoGameEngine
import games.memo.screens.MemoGameScreen
import ui.navigation.GameInfoScreen
import ui.navigation.GameScreen

@Composable
fun MainScreen(
    memoGameEngine: MemoGameEngine,
    dealGameEngine: DealGameEngine,
    hangmanGameEngine: HangmanGameEngine,
    hideAndChessGameEngine: HideAndChessGameEngine,
    findTheStarGameEngine: FindTheStarGameEngine,
    battleshipGameEngine: BattleshipGameEngine,
    mastermindGameEngine: MastermindGameEngine,
    kimGameEngine: KimGameEngine,
) {
    var gameInfoScreen: GameInfoScreen by remember { mutableStateOf(GameInfoScreen.None) }
    var screen: GameScreen by remember { mutableStateOf(GameScreen.HOME) }

    Scaffold(
        topBar = {
            if (screen != GameScreen.HOME) {
                Toolbar(
                    title = screen.title,
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

            is GameInfoScreen.Hangman -> GameInfoScreen(
                title = (gameInfoScreen as GameInfoScreen.Hangman).title,
                content = (gameInfoScreen as GameInfoScreen.Hangman).content,
                image = (gameInfoScreen as GameInfoScreen.Hangman).image,
                onBackClick = { gameInfoScreen = GameInfoScreen.None },
                gameEngine = hangmanGameEngine,
            )

            GameInfoScreen.None -> {
                GamesNavigation(
                    screen = screen,
                    memoGameEngine = memoGameEngine,
                    dealGameEngine = dealGameEngine,
                    hangmanGameEngine = hangmanGameEngine,
                    hideAndChessGameEngine = hideAndChessGameEngine,
                    findTheStarGameEngine = findTheStarGameEngine,
                    battleshipGameEngine = battleshipGameEngine,
                    mastermindGameEngine = mastermindGameEngine,
                    kimGameEngine = kimGameEngine,
                    onGameCardClick = { screen = it },
                    onInfoIconClick = { gameInfoScreen = it },
                )
            }

            is GameInfoScreen.FindTheStar -> GameInfoScreen(
                title = (gameInfoScreen as GameInfoScreen.FindTheStar).title,
                content = (gameInfoScreen as GameInfoScreen.FindTheStar).content,
                image = (gameInfoScreen as GameInfoScreen.FindTheStar).image,
                onBackClick = { gameInfoScreen = GameInfoScreen.None },
                gameEngine = findTheStarGameEngine,
            )
            is GameInfoScreen.HideAndChess -> GameInfoScreen(
                title = (gameInfoScreen as GameInfoScreen.HideAndChess).title,
                content = (gameInfoScreen as GameInfoScreen.HideAndChess).content,
                image = (gameInfoScreen as GameInfoScreen.HideAndChess).image,
                onBackClick = { gameInfoScreen = GameInfoScreen.None },
                gameEngine = hideAndChessGameEngine,
            )
            is GameInfoScreen.Battleship -> GameInfoScreen(
                title = (gameInfoScreen as GameInfoScreen.Battleship).title,
                content = (gameInfoScreen as GameInfoScreen.Battleship).content,
                image = (gameInfoScreen as GameInfoScreen.Battleship).image,
                onBackClick = { gameInfoScreen = GameInfoScreen.None },
                gameEngine = battleshipGameEngine,
            )
            is GameInfoScreen.KimGame -> GameInfoScreen(
                title = (gameInfoScreen as GameInfoScreen.KimGame).title,
                content = (gameInfoScreen as GameInfoScreen.KimGame).content,
                image = (gameInfoScreen as GameInfoScreen.KimGame).image,
                onBackClick = { gameInfoScreen = GameInfoScreen.None },
                gameEngine = kimGameEngine,
            )
            is GameInfoScreen.Mastermind -> GameInfoScreen(
                title = (gameInfoScreen as GameInfoScreen.Mastermind).title,
                content = (gameInfoScreen as GameInfoScreen.Mastermind).content,
                image = (gameInfoScreen as GameInfoScreen.Mastermind).image,
                onBackClick = { gameInfoScreen = GameInfoScreen.None },
                gameEngine = mastermindGameEngine,
            )
        }
    }
}

@Composable
private fun GamesNavigation(
    screen: GameScreen,
    memoGameEngine: MemoGameEngine,
    dealGameEngine: DealGameEngine,
    hangmanGameEngine: HangmanGameEngine,
    hideAndChessGameEngine: HideAndChessGameEngine,
    findTheStarGameEngine: FindTheStarGameEngine,
    battleshipGameEngine: BattleshipGameEngine,
    mastermindGameEngine: MastermindGameEngine,
    kimGameEngine: KimGameEngine,
    onGameCardClick: (GameScreen) -> Unit,
    onInfoIconClick: (GameInfoScreen) -> Unit,
) {
    val memoState by memoGameEngine.state.collectAsState()
    val dealState by dealGameEngine.state.collectAsState()
    val hangmanState by hangmanGameEngine.state.collectAsState()
    val hideAndChessState by hideAndChessGameEngine.state.collectAsState()
    val findTheStarState by findTheStarGameEngine.state.collectAsState()
    val battleshipState by battleshipGameEngine.state.collectAsState()
    val mastermindState by mastermindGameEngine.state.collectAsState()
    val kimState by kimGameEngine.state.collectAsState()

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
            state = memoState,
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

        GameScreen.HIDE_AND_CHESS -> if (hideAndChessState.selectedLevel != null) {
            HideAndChessScreen(
                state = hideAndChessState,
                onCellClick = hideAndChessGameEngine::onCellClick,
                onCellLongClick = hideAndChessGameEngine::onCellLongClick,
                onRevealClick = hideAndChessGameEngine::onRevealClick,
                onRandomChessPieceRevealClick = hideAndChessGameEngine::onRandomChessPieceRevealClick,
                onRandomXMarkRevealClick = hideAndChessGameEngine::onRandomXMarkRevealClick,
                onResetClick = hideAndChessGameEngine::onResetClick,
                onChooseLevelClick = hideAndChessGameEngine::onChooseLevelClick,
            )
        } else {
            HideAndChessLevelsScreen(
                levels = levels,
                onLevelClick = hideAndChessGameEngine::onLevelClick,
            )
        }

        GameScreen.FIND_THE_STAR -> FindTheStarScreen(
            state = findTheStarState,
            onCellClick = findTheStarGameEngine::onCellClick,
            onLevelSelected = findTheStarGameEngine::onLevelSelected,
            onPlayAgainClick = findTheStarGameEngine::onPlayAgainClick,
            onRevealBoardClick = findTheStarGameEngine::onRevealBoardClick,
        )

        GameScreen.BATTLESHIP -> BattleshipScreen(
            state = battleshipState,
            onCellClick = battleshipGameEngine::onCellClick,
            onPlayAgainClick = battleshipGameEngine::onPlayAgainClick,
            onRevealBoardClick = battleshipGameEngine::onRevealBoardClick,
        )

        GameScreen.MASTERMIND -> MastermindScreen(
            state = mastermindState,
            onCellClick = mastermindGameEngine::onCellClick,
            onPlayAgainClick = mastermindGameEngine::onPlayAgainClick,
            onRevealBoardClick = mastermindGameEngine::onRevealBoardClick,
        )

        GameScreen.KIM_GAME -> KimGameScreen(
            state = kimState,
            onPlayAgainClick = kimGameEngine::onPlayAgainClick,
            onRevealBoardClick = kimGameEngine::onRevealBoardClick,
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
    GameScreen.HANGMAN -> GameInfoScreen.Hangman()
    GameScreen.HIDE_AND_CHESS -> GameInfoScreen.HideAndChess()
    GameScreen.FIND_THE_STAR -> GameInfoScreen.FindTheStar()
    GameScreen.BATTLESHIP -> GameInfoScreen.Battleship()
    GameScreen.MASTERMIND -> GameInfoScreen.Mastermind()
    GameScreen.KIM_GAME -> GameInfoScreen.KimGame()
}

private fun List<GameScreen>.filterValidMenuItems(): List<GameScreen> = filterNot {
    it == GameScreen.HOME
}
