package de.oneandone.salesmanandroidapp.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.oneandone.salesmanandroidapp.R
import de.oneandone.salesmanandroidapp.list.models.SalesmanItemData
import de.oneandone.salesmanandroidapp.ui.theme.SalesmanTheme
import de.oneandone.salesmanandroidapp.ui.views.PersonInfoItem
import de.oneandone.salesmanandroidapp.ui.views.SimpleSearchBar
import de.oneandone.salesmanandroidapp.ui.views.SimpleTopAppBar

@Composable
fun SalesmanListScreen() {
    Scaffold(
        topBar = {
            SimpleTopAppBar(title = stringResource(id = R.string.salesman_list_title))
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            SimpleSearchBar(
                query = "",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp)
            )
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(top = 24.dp)
            ) {
                items(20) {
                    PersonInfoItem(
                        data = SalesmanItemData(
                            initials = "A",
                            name = "Anna Müller",
                            postCodeInfo = "73133, 76131"
                        )
                    )
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onSurface,
                        thickness = 1.dp,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SalesmanListScreenPreview() {
    SalesmanTheme {
        SalesmanListScreen()
    }
}