package com.crud.application.controller

import com.crud.application.entity.User
import com.crud.application.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/users")
class UserController(@Autowired private val userService: UserService) {

    @PostMapping("/add")
    fun addUser(
        @RequestParam("name") name: String,
        @RequestParam("email") email: String,
        @RequestParam("phone") phone: String,
        @RequestParam("gender") gender: String,
        @RequestParam("role") role: String,
        @RequestParam("profilepic") profilepic: MultipartFile
    ): ResponseEntity<String> {
        userService.addUser(name, email, phone, gender, role, profilepic.bytes)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/update/{id}")
    fun updateUser(
        @PathVariable id: Int,
        @RequestParam("name") name: String,
        @RequestParam("email") email: String,
        @RequestParam("phone") phone: String,
        @RequestParam("gender") gender: String,
        @RequestParam("role") role: String,
        @RequestParam("profilepic") profilepic: MultipartFile
    ): ResponseEntity<String> {
        userService.updateUser(id, name, email, phone, gender, role, profilepic.bytes)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<String> {
        userService.deleteUser(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/all")
    fun getAllUsers(): ResponseEntity<List<User>> {
        val users = userService.getAllUsers()
        return ResponseEntity(users, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Int): ResponseEntity<User?> {
        val user = userService.getUserById(id)
        return ResponseEntity(user, HttpStatus.OK)
    }
    @GetMapping("/profilepic/{id}")
    fun getProfilePic(@PathVariable id: Int): ResponseEntity<ByteArray> {
        val profilePic: ByteArray? = userService.getProfilePicById(id)
        return if (profilePic != null) {
            val headers = HttpHeaders()
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=profilepic_$id.jpg")
            headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg")
            ResponseEntity(profilePic, headers, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
