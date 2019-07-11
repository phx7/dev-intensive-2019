package ru.skillbranch.devintensive.models

class Chat (
    val id: String,
    val members: MutableCollection<User> = mutableListOf(),
    val messages: MutableCollection<BaseMessage> = mutableListOf()
) {
}