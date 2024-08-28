package eu.tutorials.loginandsignupwithfirebase

data class ChatMessage(
    val message: String,
    val timestamp: Long,
    val isSent: Boolean
)