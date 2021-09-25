package com.squaredcandy.antenna.ui.compose

import android.content.res.Resources
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.squaredcandy.antenna.R
import com.squaredcandy.antenna.ui.util.StringResource
import com.squaredcandy.antenna.ui.util.resolve

@Composable
fun RadioButtonWithLabel(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    text: String,
    textContentDescription: String? = null,
) {
    val stateDescription = if (selected) RadioButtonWithLabelStringResource.Selected.resolve()
    else RadioButtonWithLabelStringResource.Unselected.resolve()
    val contentDescription = RadioButtonWithLabelStringResource.DoubleTapToSelect(
        textContentDescription ?: text
    ).resolve()

    Row(
        modifier = modifier.semantics(true) {
            this.stateDescription = stateDescription
            this.contentDescription = contentDescription
            this.role = Role.RadioButton
        }
    ) {
        RadioButton(
            modifier = modifier.clearAndSetSemantics {},
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high)
            )
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            modifier = modifier.clearAndSetSemantics {},
            text = text,
        )
    }
}

private sealed class RadioButtonWithLabelStringResource : StringResource {
    object Selected : RadioButtonWithLabelStringResource()
    object Unselected : RadioButtonWithLabelStringResource()
    data class DoubleTapToSelect(val text: String) : RadioButtonWithLabelStringResource()

    override fun resolve(resources: Resources): String {
        return when (this) {
            Selected -> resources.getString(R.string.radio_selected)
            Unselected -> resources.getString(R.string.radio_unselected)
            is DoubleTapToSelect -> resources.getString(R.string.double_tap_to_select, text)
        }
    }
}