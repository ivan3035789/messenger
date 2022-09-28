package com.example.messenger.api.services

import com.example.messenger.api.exceptions.ConversationIdInvalidException
import com.example.messenger.api.models.Conversation
import com.example.messenger.api.models.User
import com.example.messenger.api.repositaries.ConversationRepository
import org.springframework.stereotype.Service

@Service
class ConversationServiceImpl(val repository: ConversationRepository) : ConversationService {

    override fun createConversation(userA: User, userB: User) : Conversation {
        val conversation = Conversation(userA, userB)
        repository.save(conversation)
        return conversation
    }

    override fun conversationExists(userA: User, userB: User): Boolean {
        return if (repository.findBySenderIdRAndRecipient(userA.id, userB.id) != null)
            true
        else repository.findBySenderIdRAndRecipient(userA.id, userB.id) != null
    }

    override fun getConversation(userA: User, userB: User): Conversation? {
        return when {
            repository.findBySenderIdRAndRecipient(userA.id, userB.id) != null ->
            repository.findBySenderIdRAndRecipient(userA.id, userB.id)
            repository.findBySenderIdRAndRecipient(userB.id, userA.id) != null ->
            repository.findBySenderIdRAndRecipient(userB.id, userA.id) else -> null
        }
    }

    override fun retrieveThread(conversationId: Long): Conversation {
        val conversation = repository.findById(conversationId)
        if (conversation.isPresent) {
            return conversation.get()
        }
        throw ConversationIdInvalidException("Invalid conversation id '$conversationId'")
    }

    override fun listUserConversation(userId: Long): List<Conversation> {
        val conversationList: ArrayList<Conversation> = ArrayList()
        conversationList.addAll(repository.findBySenderId(userId))
        conversationList.addAll(repository.findByRecipientId(userId))
        return conversationList
    }

    override fun nameSecondParty(conversation: Conversation, userId: Long): String {
        return if (conversation.sender?.id == userId) {
            conversation.recipient?.userName as String
        } else {
            conversation.sender?.userName as String
        }
    }
}