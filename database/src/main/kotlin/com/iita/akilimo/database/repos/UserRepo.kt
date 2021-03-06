package com.iita.akilimo.database.repos

import com.iita.akilimo.database.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepo : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): Optional<UserEntity>
}
