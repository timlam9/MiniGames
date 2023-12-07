import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.remember

class RecompositionCounter(var value: Int)

@Composable
inline fun LogCompositions(msg: String, tag: String = "") {
        val recompositionCounter = remember { RecompositionCounter(0) }

        println("$tag-> $msg | ${recompositionCounter.value} $currentRecomposeScope")
        recompositionCounter.value++
}
