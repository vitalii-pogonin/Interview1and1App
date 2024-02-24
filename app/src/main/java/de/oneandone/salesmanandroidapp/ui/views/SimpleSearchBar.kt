package de.oneandone.salesmanandroidapp.ui.views

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.oneandone.salesmanandroidapp.R
import de.oneandone.salesmanandroidapp.ui.theme.SalesmanTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        shape = RectangleShape,
        modifier = modifier.fillMaxWidth(),
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        BasicTextField(
            value = query,
            onValueChange = {
                if (it.isEmpty() ||
                    (it.length <= SEARCH_INPUT_SIZE && it.matches(SEARCH_INPUT_REGEX))
                ) {
                    onQueryChange(it)
                }
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = query,
                    innerTextField = innerTextField,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search_bar_placeholder),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    },
                    leadingIcon = {
                        Icon(
                            contentDescription = null,
                            imageVector = Icons.Default.Search,
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    },
                    trailingIcon = {
                        Icon(
                            contentDescription = null,
                            imageVector = Icons.Default.Mic,
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    },
                    singleLine = true,
                    enabled = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        disabledContainerColor = MaterialTheme.colorScheme.surface,
                        errorContainerColor = MaterialTheme.colorScheme.surface,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),
                )
            },
            textStyle = MaterialTheme.typography.titleLarge
                .copy(color = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private const val SEARCH_INPUT_SIZE = 5
private val SEARCH_INPUT_REGEX = Regex("[0-9]+")

@Preview(showSystemUi = true)
@Composable
fun SimpleSearchBarPreview() {
    SalesmanTheme {
        SimpleSearchBar(
            query = "",
            onQueryChange = {}
        )
    }
}