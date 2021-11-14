package org.europeana.testeuropeana.controller

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.europeana.testeuropeana.repository.ResultEntity
import org.europeana.testeuropeana.service.CalculateService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RestControllerTests {

    lateinit var controller: RestController
    lateinit var service: CalculateService

    @BeforeEach
    fun setup() {
        service = mockk()
        controller = RestController(service)
    }

    @Test
    fun ping() {
        // Setup

        // Act
        val result = controller.ping()

        // Assert
        assertThat(result).isEqualTo("pong")
    }

    @Test
    fun setUpperValue() {
        // Setup
        val entity = ResultEntity()
        entity.upperNumber = 10L
        every { service.save(any()) } returns entity

        // Act
        val result = controller.setUpperValue(10L)

        // Assert
        assertThat(result).isNotNull
        assertThat(result).isEqualTo(10L)
    }

    @Test
    fun calculate() {
        // Setup
        val entity = ResultEntity()
        entity.id = 1
        every { service.calculate(10L) } returns entity

        // Act
        val result = controller.calculate(10L)

        // Assert
        assertThat(result).isNotNull
        assertThat(result.id).isEqualTo(1)
    }
}