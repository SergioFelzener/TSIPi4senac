package br.app.pi4mobile.models.response

import br.app.pi4mobile.models.User

data class LoginResponse (val user: User, val token: String)