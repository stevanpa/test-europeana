package org.europeana.testeuropeana.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class RestController {

    var log: Logger = LoggerFactory.getLogger(RestController::class.java)

    @GetMapping("/ping")
    fun ping(): String {
        log.info("Pinging system")

        return "pong"
    }

    @GetMapping("/upper/{value}")
    fun setUpperValue(value: Int) {
        log.info("Set upper value: $value")
    }

    @GetMapping("/calculate")
    fun calculate() {
        log.info("Calculate result")
    }
}