package ui

import java.io.File

fun getImagePath(index: Int): String? {
    val home = System.getProperty("user.home")
    var file = File("$home/Downloads/memo/image$index.png")

    if (!file.exists()) {
        file = File("$home/Downloads/memo/image$index.jpg")
    }

    if (!file.exists()) {
        file = File("$home/Downloads/memo/image$index.jpeg")
    }

    if (!file.exists()) {
        println("Image doesn't exist!")
        return null
    }

    println("Image path: ${file.absolutePath}")
    return file.absolutePath
}
