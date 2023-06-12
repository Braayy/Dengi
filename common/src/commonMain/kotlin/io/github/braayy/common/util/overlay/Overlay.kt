package io.github.braayy.common.util.overlay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// TODO: Try to find a way to automatically ignore the interactions outside the modal when one of then are open.
//  For now, this is done manually by passing the modal state to all interact-ables composables down the line.

@DslMarker
annotation class OverlayDsl

@OverlayDsl
object OverlayContainerScope

@Composable
inline fun OverlayContainer(
    content: @Composable OverlayContainerScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        content(OverlayContainerScope)
    }
}