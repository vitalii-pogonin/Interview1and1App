package de.oneandone.salesmanandroidapp.list.usecase

import de.oneandone.salesmanandroidapp.list.models.SalesmanDto
import io.realm.kotlin.Realm
import javax.inject.Inject

class SalesmanRepositoryRealm @Inject constructor(
    private val realm: Realm
) : SalesmanRepository {
    override fun fetchList(): List<SalesmanEntity> {
        return realm.query(SalesmanDto::class).find()
            .map(::mapToEntity)
    }

    private fun mapToEntity(dto: SalesmanDto) = SalesmanEntity(
        name = dto.name,
        areas = dto.areas
    )
}