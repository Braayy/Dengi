package io.github.braayy.common.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import java.nio.file.InvalidPathException
import java.nio.file.Paths
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.filechooser.FileSystemView

//TODO: Make this multiplatform, for now it is just using JFileChooser, a swing api, so android will not work for now.
@Composable
fun FileChooser(
    show: Boolean,
    title: String,
    operation: FileChooserOperation,
    fileFilter: FileFilter,
    onFileSelected: (String) -> Unit,
    onError: (FileChooserError) -> Unit = {},
) {
    LaunchedEffect(show) {
        if (show) {
            val chooser = JFileChooser(FileSystemView.getFileSystemView().homeDirectory).apply {
                dialogTitle = title
                fileSelectionMode = JFileChooser.FILES_ONLY
                isMultiSelectionEnabled = false
                isAcceptAllFileFilterUsed = false
                addChoosableFileFilter(FileNameExtensionFilter(fileFilter.description, fileFilter.extension))
            }

            val result = when (operation) {
                FileChooserOperation.Open -> chooser.showOpenDialog(null)
                FileChooserOperation.Save -> chooser.showSaveDialog(null)
            }

            if (result == JFileChooser.APPROVE_OPTION) {
                val pathWithExtension = "${chooser.selectedFile.absolutePath}.${fileFilter.extension}"

                try {
                    Paths.get(pathWithExtension)

                    onFileSelected(pathWithExtension)
                } catch (ignore: InvalidPathException) {
                    onError(FileChooserError.InvalidFilePath)
                }
            } else {
                onError(FileChooserError.NoFileSelected)
            }
        }
    }
}

data class FileFilter(
    val description: String,
    val extension: String,
)

enum class FileChooserOperation {
    Open,
    Save,
}

enum class FileChooserError {
    NoFileSelected,
    InvalidFilePath,
}