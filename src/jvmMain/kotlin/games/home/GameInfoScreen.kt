package games.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import games.deal.DealGameEngine
import GameEngine
import games.memo.MemoGameEngine
import games.deal.components.DealCard
import ui.theme.MiniButton
import ui.theme.MiniIconButton
import ui.theme.MiniInput
import games.deal.model.DealItem
import java.util.UUID

@Composable
fun GameInfoScreen(
    title: String,
    content: String,
    image: String,
    onBackClick: () -> Unit,
    gameEngine: GameEngine,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.DarkGray).padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = title,
                style = MaterialTheme.typography.h3,
                color = Color.White,
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(34.dp)
                )
            }
        }
        Text(
            text = content,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(20.dp),
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier.fillMaxWidth().height(240.dp),
            painter = painterResource(image),
            contentDescription = "",
            contentScale = ContentScale.Fit,
        )
        when (gameEngine) {
            is MemoGameEngine -> Unit
            is DealGameEngine -> {
                Spacer(modifier = Modifier.height(10.dp))

                val state = gameEngine.state.collectAsState()
                val dealItems = state.value.list.items
                val gridState = rememberLazyGridState()
                var prize by remember { mutableStateOf("") }
                var type by remember { mutableStateOf(DealItem.Type.BLUE) }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp).align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        MiniInput(initialValue = prize, label = "prize") {
                            prize = it
                        }
                        MiniButton(title = "Type: ${type.name}", color = type.getColor(), contentColor = Color.White) {
                            type = if (type == DealItem.Type.BLUE) DealItem.Type.RED else DealItem.Type.BLUE
                        }
                        MiniButton(title = "Add", color = Color.Green, contentColor = Color.White) {
                            gameEngine.onAddClick(
                                DealItem(
                                    id = UUID.randomUUID().toString(),
                                    title = prize,
                                    opened = false,
                                    type = type,
                                )
                            )
                            prize = ""
                        }
                    }
                    LazyVerticalGrid(
                        state = gridState,
                        columns = GridCells.Adaptive(minSize = 300.dp),
                        contentPadding = PaddingValues(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        items(
                            items = dealItems,
                            key = { item: DealItem -> item.id },
                        ) { item ->
                            Row(
                                modifier = Modifier.wrapContentSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround,
                            ) {
                                DealCard(
                                    title = item.title,
                                    color = item.type.getColor(),
                                    isOpened = false,
                                    onClick = {},
                                )
                                MiniIconButton(color = Color.Red) { gameEngine.onDeleteClick(item.id) }
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

private fun DealItem.Type.getColor() = when (this) {
    DealItem.Type.BLUE -> Color.Blue
    DealItem.Type.RED -> Color.Red
}
