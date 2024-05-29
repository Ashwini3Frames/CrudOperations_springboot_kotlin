package com.crud.application.repository

import com.crud.application.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Int> {

    @Procedure(name = "addUser")
    fun addUser(
        @Param("p_name") name: String,
        @Param("p_email") email: String,
        @Param("p_phone") phone: String,
        @Param("p_gender") gender: String,
        @Param("p_role") role: String,
        @Param("p_profilepic") profilepic: ByteArray
    )

    @Procedure(name = "updateUser")
    fun updateUser(
        @Param("p_id") id: Int,
        @Param("p_name") name: String,
        @Param("p_email") email: String,
        @Param("p_phone") phone: String,
        @Param("p_gender") gender: String,
        @Param("p_role") role: String,
        @Param("p_profilepic") profilepic: ByteArray
    )

    @Procedure(name = "deleteUser")
    fun deleteUser(@Param("p_id") id: Int)

    @Procedure(name = "getAllUsers")
    fun getAllUsers(): List<User>

    // Override findById method from JpaRepository
    override fun findById(id: Int): Optional<User>
}
