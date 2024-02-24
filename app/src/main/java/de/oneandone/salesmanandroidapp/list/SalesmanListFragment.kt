package de.oneandone.salesmanandroidapp.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import de.oneandone.salesmanandroidapp.ui.theme.SalesmanTheme

@AndroidEntryPoint
class SalesmanListFragment : Fragment() {
    private val viewModel by viewModels<SalesmanListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SalesmanTheme {
                    val queryState by viewModel.searchQuery.collectAsStateWithLifecycle()
                    val salesmanListState by viewModel.salesmans.collectAsStateWithLifecycle()
                    SalesmanListScreen(
                        searchQuery = queryState,
                        onQueryChange = viewModel::onSearchQueryChange,
                        salesmanList = salesmanListState
                    )
                }
            }
        }
    }
}