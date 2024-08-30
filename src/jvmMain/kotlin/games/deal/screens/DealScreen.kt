package games.deal.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import games.deal.components.DealCard
import games.deal.model.DealItem
import games.deal.model.DealState

@Composable
fun DealScreen(
    state: DealState,
    onItemClick: (id: String) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource("home/deal.jpeg"),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                items(
                    items = state.list.items.filter { it.type == DealItem.Type.BLUE },
                    key = { item -> item.id },
                ) { item ->
                    DealCard(
                        title = item.title,
                        color = Color.Blue,
                        isOpened = item.opened,
                        onClick = { onItemClick(item.id) },
                    )
                }
            }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                items(
                    items = state.list.items.filter { it.type == DealItem.Type.RED },
                    key = { item -> item.id },
                ) { item ->
                    DealCard(
                        title = item.title,
                        color = Color.Red,
                        isOpened = item.opened,
                        onClick = { onItemClick(item.id) },
                    )
                }
            }
        }
    }
}
