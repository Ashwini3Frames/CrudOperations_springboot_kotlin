package com.crud.application.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
@Entity
@Table(name = "users", schema = "user_data")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val name: String,
    val email: String,
    val phone: String,
    val gender: String,
    val role: String,
    val profilepic: ByteArray
)

