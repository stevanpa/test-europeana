package org.europeana.testeuropeana.service

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.europeana.testeuropeana.repository.NumberRepository
import org.europeana.testeuropeana.repository.ResultEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CalculateServiceTests {

    lateinit var service: CalculateService
    lateinit var repository: NumberRepository

    @BeforeEach
    fun setup() {
        repository = mockk()
        service = CalculateService(repository)
    }

    @Test
    fun save() {
        // Setup
        val entity = ResultEntity(1, 10L, 1000L, 123454)
        every { repository.save(any()) } returns entity

        // Act
        val result = service.save(10L)

        // Assert
        assertThat(result).isNotNull
        assertThat(result.id).isEqualTo(1L)
    }

    @Test
    fun calculate() {
        // Setup
        val entity = ResultEntity(1, 10L, null, null)
        every { repository.findByUpperNumber(any()) } returns null
        every { repository.save(any()) } returns entity

        // Act
        val result = service.calculate(entity.upperNumber)

        // Assert
        assertThat(result).isNotNull
        assertThat(result.smallestPositiveNumber).isEqualTo(2520)
    }

}