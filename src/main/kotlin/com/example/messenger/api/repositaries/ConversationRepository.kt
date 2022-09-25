package com.example.messenger.api.repositaries

import com.example.messenger.api.models.Conversation
import org.springframework.data.repository.CrudRepository

interface ConversationRepository : CrudRepository<Conversation, Long> {
    fun findBySenderId(id: Long): List<Conversation>
    fun findByRecipientId(id: Long): List<Conversation>
    fun findBySenderIdRAndRecipient(sender: Long, recipientId: Long): Conversation?
}