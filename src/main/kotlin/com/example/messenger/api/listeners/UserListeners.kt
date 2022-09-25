package com.example.messenger.api.listeners

import com.example.messenger.api.models.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

class UserListeners {
    @PrePersist
    @PreUpdate
    fun hashPassword(user: User) {
        user.password = BCryptPasswordEncoder().encode(user.password)
    }

}