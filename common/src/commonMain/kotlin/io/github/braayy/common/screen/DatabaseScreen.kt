package io.github.braayy.common.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import io.github.braayy.common.model.Database
import io.github.braayy.common.util.overlay.*

@Composable
fun DatabaseScreen(
    database: Database,
) {
    var notificationText by remember { mutableStateOf<String?>(null) }
    var showModal by rememberModalState()

    OverlayContainer {
        Column {

        }

        Modal(
            show = showModal,
            modifier = Modifier
        ) {
            ModalControls(
                okText = "",
                cancelText = "",
                onOkClick = {},
                onCancelClick = {},
            )
        }

        notificationText?.let {
            Notification(
                color = MaterialTheme.colors.error,
                text = it
            )
        }
    }
}
