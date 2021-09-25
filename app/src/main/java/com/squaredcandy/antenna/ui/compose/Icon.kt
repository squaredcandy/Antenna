package com.squaredcandy.antenna.ui.compose

import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Icon(
    iconResource: IconResource,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
) {
    androidx.compose.material.Icon(
        imageVector = iconResource.resolveIcon(),
        contentDescription = iconResource.resolveContentDescription(),
        modifier = modifier,
        tint = tint,
    )
}