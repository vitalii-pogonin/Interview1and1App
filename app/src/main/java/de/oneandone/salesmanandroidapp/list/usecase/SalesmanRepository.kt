package de.oneandone.salesmanandroidapp.list.usecase

import javax.inject.Inject

interface SalesmanRepository {
    fun fetchList(): List<SalesmanEntity>
}

class SalesmanRepositoryImpl @Inject constructor() : SalesmanRepository {
    override fun fetchList(): List<SalesmanEntity> = listOf(
        SalesmanEntity(name = "Artem Titarenko", listOf("76133")),
        SalesmanEntity(name = "Bernd Schmitt", listOf("7619*")),
        SalesmanEntity(name = "Chris Krapp", listOf("762*")),
        SalesmanEntity(name = "Alex Uber", listOf("86*"))
    )
}