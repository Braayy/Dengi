import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.braayy.common.App


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
