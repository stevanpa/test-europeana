package org.europeana.testeuropeana.controller

import org.europeana.testeuropeana.repository.ResultEntity
import org.europeana.testeuropeana.service.CalculateService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class RestController @Autowired constructor(
    var service: CalculateService
) {

    var log: Logger = LoggerFactory.getLogger(RestController::class.java)


    @GetMapping("/ping")
    fun ping(): String {
        log.info("Pinging system")

        return "pong"
    }

    @PostMapping("/upper/{value}")
    fun setUpperValue(@PathVariable value: Long): Long {
        log.info("Set upper value: $value")

        val resultEntity = service.save(value)

        log.info("Saved ResultEntity: $resultEntity")

        return resultEntity.upperNumber
    }

    @GetMapping(
        path = ["/calculate/{value}"],
        produces = [ MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.TEXT_XML_VALUE ] )
    @ResponseBody
    fun calculate(@PathVariable value: Long): ResultEntity {
        log.info("Calculate result for value: $value")

        return service.calculate(value)
    }
}