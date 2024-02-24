package de.oneandone.salesmanandroidapp.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.oneandone.salesmanandroidapp.list.models.SalesmanItemData
import de.oneandone.salesmanandroidapp.ui.theme.SalesmanTheme

@Composable
fun PersonInfoItem(
    data: SalesmanItemData,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val arrowAngle by remember(expanded) {
        mutableFloatStateOf(if (expanded) 0f else -90f)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Avatar(
            initials = data.initials,
            modifier.padding(vertical = 1.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .sizeIn(minHeight = 42.dp)
                .wrapContentHeight(align = Alignment.CenterVertically),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = data.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
            AnimatedVisibility(visible = expanded) {
                Text(
                    text = data.postCodeInfo,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
        Icon(
            contentDescription = null,
            imageVector = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .padding(top = 14.dp)
                .rotate(arrowAngle)
                .clickable { expanded = !expanded },
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PersonInfoItemPreview() {
    SalesmanTheme {
        PersonInfoItem(
            data = SalesmanItemData(
                initials = "A",
                name = "Anna Müller",
                postCodeInfo = "73133, 76131\n73133, 76131\n73133, 76131"
            )
        )
    }
}