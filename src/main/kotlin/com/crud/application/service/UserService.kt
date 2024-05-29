package com.crud.application.service

import com.crud.application.entity.User
import com.crud.application.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils
import java.util.Optional

@Service
class UserService(private val userRepository: UserRepository) {

    fun addUser(name: String, email: String, phone: String, gender: String, role: String, profilepic: ByteArray) {
        validateUser(name, email, phone, gender, role)
        userRepository.addUser(name, email, phone, gender, role, profilepic)
    }

    fun updateUser(id: Int, name: String, email: String, phone: String, gender: String, role: String, profilepic: ByteArray) {
        userRepository.updateUser(id, name, email, phone, gender, role, profilepic)
    }

    fun deleteUser(id: Int) {
        userRepository.deleteUser(id)
    }
    @Transactional
    fun getAllUsers(): List<User> {
        return userRepository.getAllUsers()
    }

    fun getUserById(id: Int): User? {
        return userRepository.findById(id).orElse(null)
    }
    fun getProfilePicById(id: Int): ByteArray? {
        val user: User? = userRepository.findById(id).orElse(null)
        return user?.profilepic
    }

    private fun validateUser(name: String, email: String, phone: String, gender: String, role: String) {
        if (!StringUtils.hasText(name)) {
            throw IllegalArgumentException("Name cannot be empty")
        }
        if (!StringUtils.hasText(email) || !email.contains("@")) {
            throw IllegalArgumentException("Invalid email address")
        }
        if (!StringUtils.hasText(phone) || !phone.matches(Regex("^[0-9]{10}$"))) {
            throw IllegalArgumentException("Invalid phone number")
        }
        if (!StringUtils.hasText(gender) || !(gender == "male" || gender == "female" || gender == "other")) {
            throw IllegalArgumentException("Gender must be male, female, or other")
        }
        if (!StringUtils.hasText(role)) {
            throw IllegalArgumentException("Role cannot be empty")
        }
    }
}
