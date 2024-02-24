package de.oneandone.salesmanandroidapp.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.oneandone.salesmanandroidapp.list.models.SalesmanItemData
import de.oneandone.salesmanandroidapp.list.usecase.SalesmanListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class SalesmanListViewModel @Inject constructor(
    private val useCase: SalesmanListUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val salesmans: StateFlow<List<SalesmanItemData>> = _searchQuery
        .debounce(SEARCH_DEBOUNCE_TIME)
        .mapLatest { query ->
            useCase.getListByZip(query).map { salesman ->
                SalesmanItemData(
                    salesman.name.first().toString(),
                    salesman.name,
                    salesman.areas.joinToString(AREAS_SEPARATOR)
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun onSearchQueryChange(text: String) {
        _searchQuery.update { text }
    }

    companion object {
        private const val SEARCH_DEBOUNCE_TIME = 1_000L
        private const val AREAS_SEPARATOR = ", "
    }
}