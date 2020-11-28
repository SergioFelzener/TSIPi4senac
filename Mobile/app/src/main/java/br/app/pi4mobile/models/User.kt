package br.app.pi4mobile.models

data class User(
    val id: Int,
    val email: String?,
    val name: String?,
    val password: String?,
    val profile_photo_url: String?,
    val token: String?
)