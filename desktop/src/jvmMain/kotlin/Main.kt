import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import io.github.braayy.common.App
import io.github.braayy.common.DengiTheme


fun main() = application {
    Window(
        title = "Dengi",
        resizable = false,
        state = WindowState(
            size = DpSize(800.dp, 600.dp),
            position = WindowPosition(Alignment.Center),
        ),
        onCloseRequest = ::exitApplication
    ) {
        DengiTheme {
            App()
        }
    }
}
