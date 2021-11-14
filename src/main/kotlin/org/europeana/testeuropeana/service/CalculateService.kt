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

    fun calculate(upperNumber: Long): ResultEntity {
        var resultEntity = repository.findByUpperNumber(upperNumber)

        if (resultEntity == null) {
            resultEntity = save(upperNumber)
        }

        if (resultEntity.smallestPositiveNumber == null) {

            val elapsed = measureTimeMillis {
                synchronousProcessing(resultEntity)
            }

            log.info("Elapsed time: $elapsed ms and Result: ${resultEntity.smallestPositiveNumber} \n")

            resultEntity.measureTimeMillis = elapsed
            repository.save(resultEntity)
        } else {
            log.info("Result already calculated for: ${resultEntity.upperNumber}. Returning value from DB $resultEntity\n")
        }

        return resultEntity
    }

    private fun synchronousProcessing(resultEntity: ResultEntity) {
        val x = resultEntity.upperNumber
        var smallestNumber = resultEntity.upperNumber.toInt()
        val result = arrayOfNulls<Boolean>(x.toInt())

        while (result.contains(false) || result.contains(null)) {
            doWhile(result, smallestNumber, x.toInt(), 1L, x)
            smallestNumber += 1
        }

        resultEntity.smallestPositiveNumber = (smallestNumber - 1).toLong()
    }

    private fun doWhile(result: Array<Boolean?>, smallestNumber: Int, size: Int, start: Long, end: Long) {
        var index = 0
        var divider = start
        while (index < size && divider <= end) {
            if ((smallestNumber % divider) != 0L) {
                result[index] = false
                break
            } else {
                result[index] = true
            }
            index += 1
            divider += 1
        }
    }

    fun save(upperNumber: Long): ResultEntity {
        val resultEntity = ResultEntity(upperNumber = upperNumber)
        return repository.save(resultEntity)
    }
}