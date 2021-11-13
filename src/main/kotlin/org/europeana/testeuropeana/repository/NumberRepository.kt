package org.europeana.testeuropeana.repository

import org.springframework.data.repository.CrudRepository

interface NumberRepository: CrudRepository<ResultEntity, Long> {

    fun findByUpperNumber(upperNumber: Long): ResultEntity?
}