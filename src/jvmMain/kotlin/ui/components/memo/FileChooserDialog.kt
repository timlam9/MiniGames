package ui.components.memo

import androidx.compose.runtime.Composable
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView

@Composable
fun FileChooserDialog(
    title: String,
    onResult: (result: File) -> Unit
) {
    val fileChooser = JFileChooser(FileSystemView.getFileSystemView())
    fileChooser.currentDirectory = File(System.getProperty("user.dir"))
    fileChooser.dialogTitle = title
    fileChooser.fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES
    fileChooser.isAcceptAllFileFilterUsed = true
    fileChooser.selectedFile = null
    fileChooser.currentDirectory = null
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        val file: File = fileChooser.selectedFile
        println("choose file or folder is: $file")
        onResult(file)
    } else {
        println("No Selection")
    }
}
