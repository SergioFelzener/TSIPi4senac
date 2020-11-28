package br.app.pi4mobile.api

data class ProductModel(


    val id: Int,
    val name: String?,
    val description: String?,
    val price: String?,
    val descount: String?, //discount?
    val quantity: String?,
    val slug: String?,
    val created_at: String?,
    val updated_at: String?,
    val amount: Int
)