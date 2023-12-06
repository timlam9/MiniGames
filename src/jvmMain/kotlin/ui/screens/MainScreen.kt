package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import MemoGameEngine

@Composable
fun MainScreen(memoGameEngine: MemoGameEngine) {
    var gameInfoScreen: GameInfoScreen by remember { mutableStateOf(GameInfoScreen.None) }
    var screen: GameScreen by remember { mutableStateOf(GameScreen.HOME) }
    val state by memoGameEngine.state.collectAsState()

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
                onBackClick = { gameInfoScreen = GameInfoScreen.None }
            )

            GameInfoScreen.None -> {
                when (screen) {
                    GameScreen.HOME -> {
                        val games = GameScreen.values().toList().filterValidMenuItems()

                        MenuScreen(
                            games = games,
                            onGameCardClick = { screen = it },
                            onInfoIconClick = { gameInfoScreen = GameInfoScreen.Memo() },
                        )
                    }

                    GameScreen.IMAGE_PICKER -> MemoGameImagePickerScreen(
                        onOkClick = { screen = GameScreen.HOME },
                        onImagePicked = { image, index -> memoGameEngine.onImagePicked(image, index) },
                    )

                    GameScreen.MEMO -> MemoGameScreen(
                        state = state,
                        onGameStart = memoGameEngine::onGameStart,
                        onCardClick = memoGameEngine::onCardClick,
                    )
                }
            }
        }
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

private fun List<GameScreen>.filterValidMenuItems(): List<GameScreen> = filterNot {
    it == GameScreen.HOME || it == GameScreen.IMAGE_PICKER
}


enum class GameScreen(val image: String, val title: String) {

    HOME("tiger.jpg", "Home"),
    IMAGE_PICKER("tiger.jpg", "Image picker"),
    MEMO("ic_memo.png", "Memo"),
}

sealed interface GameInfoScreen {

    object None : GameInfoScreen
    data class Memo(
        val title: String = "Memo info",
        val content: String = MEMO_INFO,
        val image: String = "ic_memo.png",
    ) : GameInfoScreen
}

private const val MEMO_INFO =
    "Για να παιχτεί αυτό το παιχνίδι χρειάζεται να δημιουργηθεί ο φάκελος με όνομα 'memo' μέσα στον 'downloads' φάκελο του υπολογιστή. Ο φάκελος αυτός πρέπει να περιέχει 12 εικόνες με όνομα 'image1.png', 'image2.jpg' κλπ μέχρι και την εικόνα 12. \n\nΤα είδη των εικόνων που υποστηρίζονται είναι 'jpg' και 'png'. \n\nΣκοπός του παιχνιδιού είναι να βρεθούν τα 12 ζευγάρια των εικόνων."