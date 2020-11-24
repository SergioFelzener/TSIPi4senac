package br.app.pi4mobile.models

data class Product (
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val descount: String, //discount?
    val quantity: String,
    val slug: String,
    val categories: List<Category>,
    val photos: List<Photo>
)
