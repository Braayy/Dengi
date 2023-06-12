package io.github.braayy.common.util.overlay

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.braayy.common.modalBackground

@OverlayDsl
object ModalScope

@Composable
fun rememberModalState(initialState: Boolean = false) = remember { mutableStateOf(initialState) }

@Composable
fun OverlayContainerScope.Modal(
    show: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable ModalScope.() -> Unit,
) {
    if (show) {
        Surface(
            color = MaterialTheme.colors.modalBackground,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
                .then(modifier),
        ) {
            content(ModalScope)
        }
    }
}

@Composable
fun ModalScope.ModalControls(
    okText: String,
    cancelText: String,
    onOkClick: () -> Unit,
    onCancelClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Button(
            onClick = onOkClick,
        ) {
            Text(okText)
        }

        Button(
            onClick = onCancelClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
            )
        ) {
            Text(cancelText)
        }
    }
}