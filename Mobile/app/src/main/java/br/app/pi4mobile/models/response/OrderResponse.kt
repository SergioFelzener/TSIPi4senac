package br.app.pi4mobile.models.response

import br.app.pi4mobile.models.Product

data class OrderResponse(val products: List<Product>, val price: ArrayList<String>, val amount: ArrayList<Int>, val total: String)