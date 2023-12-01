package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MemoHomeScreen(
    modifier: Modifier = Modifier,
    onImagePickerClick: () -> Unit,
    onPlayClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = onImagePickerClick) {
            Text("Pick Images")
        }
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = onPlayClick) {
            Text("Play")
        }
    }
}
