package ui

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.Density
import org.jetbrains.skia.Pixmap
import org.jetbrains.skia.SamplingMode
import org.xml.sax.InputSource
import java.io.File
import java.net.URL

fun File.scaledImage(): ImageBitmap {
    val byteArray = readBytes()
    val image = org.jetbrains.skia.Image.makeFromEncoded(byteArray)

    val dst: Pixmap = Pixmap()
    val samplingMode: SamplingMode = SamplingMode.DEFAULT
    val cache: Boolean = true

    val scaled = image.scalePixels(
        dst = dst,
        samplingMode = samplingMode,
        cache = cache,
    )

    println("Resized: $scaled, image: $image")
    return image.toComposeImageBitmap()
}

/* Loading from file with java.io API */

fun loadImageBitmap(file: File): ImageBitmap =
    file.inputStream().buffered().use(::loadImageBitmap)

fun loadSvgPainter(file: File, density: Density): Painter =
    file.inputStream().buffered().use { androidx.compose.ui.res.loadSvgPainter(it, density) }

fun loadXmlImageVector(file: File, density: Density): ImageVector =
    file.inputStream().buffered().use { androidx.compose.ui.res.loadXmlImageVector(InputSource(it), density) }

/* Loading from network with java.net API */

fun loadImageBitmap(url: String): ImageBitmap =
    URL(url).openStream().buffered().use(::loadImageBitmap)

fun loadSvgPainter(url: String, density: Density): Painter =
    URL(url).openStream().buffered().use { androidx.compose.ui.res.loadSvgPainter(it, density) }

fun loadXmlImageVector(url: String, density: Density): ImageVector =
    URL(url).openStream().buffered().use { androidx.compose.ui.res.loadXmlImageVector(InputSource(it), density) }