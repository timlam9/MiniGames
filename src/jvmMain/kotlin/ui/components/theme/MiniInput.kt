package ui.components.theme

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun MiniInput(
    modifier: Modifier = Modifier,
    initialValue: String = "",
    label: String? = null,
    onValueChange: (String) -> Unit,
) {
    var text by remember(initialValue) { mutableStateOf(initialValue) }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        label = { label?.let { Text(it) } }
    )
}