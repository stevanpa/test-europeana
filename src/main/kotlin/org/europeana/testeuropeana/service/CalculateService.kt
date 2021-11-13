package org.europeana.testeuropeana.service

import org.europeana.testeuropeana.repository.NumberRepository
import org.europeana.testeuropeana.repository.ResultEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.system.measureTimeMillis

@Service
class CalculateService @Autowired constructor(
    var repository: NumberRepository
) {

    var log: Logger = LoggerFactory.getLogger(CalculateService::class.java)

    fun calculate(upperNumber: Long) {
        var resultEntity = repository.findByUpperNumber(upperNumber)

        if (resultEntity == null) {
            resultEntity = save(upperNumber)
        }

        if (resultEntity.smallestPositiveNumber == null) {

            val x = resultEntity.upperNumber
            var smallestNumber = resultEntity.upperNumber
            val result = arrayOfNulls<Boolean>(x.toInt())

            val elapsed = measureTimeMillis {
                while (result.contains(false) || result.contains(null)) {
                    doWhile(result, smallestNumber, x)
                    smallestNumber += 1
                }
            }

            resultEntity.smallestPositiveNumber = smallestNumber -1

            log.info("Elapsed time: $elapsed ms and Result: ${resultEntity.smallestPositiveNumber} \n")

        }
    }

    private fun doWhile(result: Array<Boolean?>, smallestNumber: Long, x: Long) {
        var xD = x
        var index = x
        while (index > 0 ) {
            index -= 1
            if ((smallestNumber % xD) != 0L) {
                result[index.toInt()] = (smallestNumber % xD) == 0L
                break
            } else {
                result[index.toInt()] = (smallestNumber % xD) == 0L
            }
            xD -= 1
        }
    }

    fun save(upperNumber: Long): ResultEntity {

        val resultEntity = ResultEntity(
            id = null,
            upperNumber = upperNumber,
            smallestPositiveNumber = null)

        repository.save(resultEntity)

        return resultEntity
    }
}