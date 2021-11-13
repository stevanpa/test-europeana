package org.europeana.testeuropeana.repository

import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class ResultEntity (

    @Id @GeneratedValue var id: Long? = null,
    var upperNumber: Long,
    var smallestPositiveNumber: Long? = null

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ResultEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , upperNumber = $upperNumber , smallestPositiveNumber = $smallestPositiveNumber )"
    }
}