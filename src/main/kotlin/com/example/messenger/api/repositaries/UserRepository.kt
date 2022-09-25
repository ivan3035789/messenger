package com.example.messenger.api.repositaries
import com.example.messenger.api.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUserName(UserName: String) : User?
    fun findByPhoneNumber(phoneNumber: String) : User?
}