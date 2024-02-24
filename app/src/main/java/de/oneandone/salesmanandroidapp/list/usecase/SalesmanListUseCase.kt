package de.oneandone.salesmanandroidapp.list.usecase

import javax.inject.Inject

interface SalesmanListUseCase {
    fun getListByZip(zip: String): List<SalesmanEntity>
}

class SalesmanListUseCaseImpl @Inject constructor(
    private val repository: SalesmanRepository
) : SalesmanListUseCase {

    override fun getListByZip(zip: String): List<SalesmanEntity> {
        if (zip.isNotEmpty() && !zip.all(Char::isDigit)) return emptyList()
        if (zip.length > 5) return emptyList()
        if (zip.isEmpty()) return repository.fetchList()

        return repository.fetchList()
            .filter { salesmanEntity ->
                salesmanEntity.areas.any { area ->
                    searchInArea(area, zip)
                }
            }
    }

    private fun searchInArea(area: String, zip: String): Boolean {
        if (area.endsWith("*")) {
            val numberPart = area.substringBefore("*")
            if (zip.length < numberPart.length) {
                return area.startsWith(zip)
            }
            val rangeFrom = numberPart.padEnd(5, '0').toInt()
            val rangeTo = numberPart.padEnd(5, '9').toInt()
            val paddedZip = zip.padEnd(5, '0').toInt()
            return paddedZip in rangeFrom..rangeTo
        } else {
            return area.startsWith(zip)
        }
    }
}