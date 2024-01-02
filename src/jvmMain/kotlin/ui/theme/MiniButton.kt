package ui.theme

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MiniButton(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    contentColor: Color = contentColorFor(color),
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.height(56.dp).offset(y = 4.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = contentColor,
        ),
        onClick = onClick,
    ) {
        Text(title)
    }
}