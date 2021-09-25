package com.squaredcandy.antenna.ui.util

data class SnackbarContent(
    val message: StringResource,
    val actionLabel: StringResource? = null,
    val onActionClicked: (() -> Unit)? = null,
    val snackbarDuration: SnackbarDuration = SnackbarDuration.Short,
)

sealed class SnackbarDuration {
    object Short : SnackbarDuration()
    object Long : SnackbarDuration()
    object Indefinite : SnackbarDuration()
}

fun SnackbarDuration.resolveDuration(): androidx.compose.material.SnackbarDuration {
    return when (this) {
        SnackbarDuration.Indefinite -> androidx.compose.material.SnackbarDuration.Indefinite
        SnackbarDuration.Long -> androidx.compose.material.SnackbarDuration.Long
        SnackbarDuration.Short -> androidx.compose.material.SnackbarDuration.Short
    }
}