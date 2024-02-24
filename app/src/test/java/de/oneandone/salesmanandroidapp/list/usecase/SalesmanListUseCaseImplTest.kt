package de.oneandone.salesmanandroidapp.list.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class SalesmanListUseCaseImplTest {
    private val repository = mockk<SalesmanRepository>()
    private val useCase = SalesmanListUseCaseImpl(repository)

    @Test
    fun `should return empty list if no data present`() {
        // Arrange
        every { repository.fetchList() } returns listOf()

        // Act
        val result = useCase.getListByZip("")

        //Assert
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return empty list if query is a blank space`() {
        // Arrange
        every { repository.fetchList() } returns salesmanList

        // Act
        val result = useCase.getListByZip(" ")

        //Assert
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return empty list if query is not a number`() {
        // Arrange
        every { repository.fetchList() } returns salesmanList

        // Act
        val result = useCase.getListByZip("abc")

        //Assert
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return empty list if query is more then 5 digits`() {
        // Arrange
        every { repository.fetchList() } returns salesmanList

        // Act
        val result = useCase.getListByZip("123456")

        //Assert
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return empty list if query not match`() {
        // Arrange
        every { repository.fetchList() } returns salesmanList

        // Act
        val result = useCase.getListByZip("99999")

        //Assert
        assertThat(result).isEmpty()
    }

    @Test
    fun `should return all data if query is empty`() {
        // Arrange
        every { repository.fetchList() } returns salesmanList.take(4)

        // Act
        val result = useCase.getListByZip("")

        //Assert
        assertThat(result).hasSize(4)
    }

    @Test
    fun `should return only 3 elements for query starting with 76`() {
        // Arrange
        every { repository.fetchList() } returns salesmanList

        // Act
        val result = useCase.getListByZip("76")

        //Assert
        assertThat(result).hasSize(3)
    }

    @Test
    fun `should return Artem Titarenko elements for 76133`() {
        // Arrange
        every { repository.fetchList() } returns salesmanList

        // Act
        val result = useCase.getListByZip("76133")

        //Assert
        assertThat(result).hasSize(1)
        assertThat(result[0].name).isEqualTo("Artem Titarenko")
    }

    @Test
    fun `should return Bernd Schmitt elements for 7619`() {
        // Arrange
        every { repository.fetchList() } returns salesmanList

        // Act
        val result = useCase.getListByZip("7619")

        //Assert
        assertThat(result).hasSize(1)
        assertThat(result[0].name).isEqualTo("Bernd Schmitt")
    }

    @Test
    fun `should return Bernd Schmitt elements for 76195`() {
        // Arrange
        every { repository.fetchList() } returns salesmanList

        // Act
        val result = useCase.getListByZip("76195")

        //Assert
        assertThat(result).hasSize(1)
        assertThat(result[0].name).isEqualTo("Bernd Schmitt")
    }

    @Test
    fun `should return Max Newman and Alex Black elements for 1`() {
        // Arrange
        every { repository.fetchList() } returns salesmanList

        // Act
        val result = useCase.getListByZip("1")

        //Assert
        assertThat(result).hasSize(2)
        assertThat(result[0].name).isIn(listOf("Max Newman", "Alex Black"))
    }

    @Test
    fun `should return Max Newman and Alex Black elements for 12155`() {
        // Arrange
        every { repository.fetchList() } returns salesmanList

        // Act
        val result = useCase.getListByZip("12155")

        //Assert
        assertThat(result).hasSize(2)
        assertThat(result[0].name).isIn(listOf("Max Newman", "Alex Black"))
    }

    @Test
    fun `should return Alex Black elements for 134`() {
        // Arrange
        every { repository.fetchList() } returns salesmanList

        // Act
        val result = useCase.getListByZip("134")

        //Assert
        assertThat(result).hasSize(1)
        assertThat(result[0].name).isEqualTo("Alex Black")
    }

    companion object {
        private val salesmanList: List<SalesmanEntity> = listOf(
            SalesmanEntity(name = "Artem Titarenko", listOf("76133")),
            SalesmanEntity(name = "Bernd Schmitt", listOf("7619*")),
            SalesmanEntity(name = "Chris Krapp", listOf("762*")),
            SalesmanEntity(name = "Alex Uber", listOf("86*")),
            SalesmanEntity(name = "Max Newman", listOf("12*")),
            SalesmanEntity(name = "Alex Black", listOf("121*", "13*"))
        )
    }
}