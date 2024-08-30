package ui.design

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun DefaultText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    textColor: Color = MaterialTheme.colors.primaryVariant,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.button.copy(
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize
        ),
        modifier = modifier,
    )
}
