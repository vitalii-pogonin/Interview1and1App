package de.oneandone.salesmanandroidapp.list.usecase

import javax.inject.Inject

interface SalesmanListUseCase {
    fun getListByZip(query: String): List<SalesmanEntity>
}

class SalesmanListUseCaseImpl @Inject constructor(
    private val repository: SalesmanRepository
) : SalesmanListUseCase {

    override fun getListByZip(query: String): List<SalesmanEntity> {
        return repository.list
            .filter { salesmanEntity ->
                salesmanEntity.areas.any {
                    it.startsWith(query)
                }
            }
    }
}