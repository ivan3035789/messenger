package com.example.messenger.api.components

import com.example.messenger.api.constants.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ConversationControllerAdvance {
    @ExceptionHandler
    fun conversationInvalidException(conversationControllerAdvance: ConversationControllerAdvance) :
            ResponseEntity<ErrorResponse> {
        val res = ErrorResponse("", conversationIdInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }
}