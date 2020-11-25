package br.app.pi4mobile.models.response

import br.app.pi4mobile.models.Category
import br.app.pi4mobile.models.Product

data class CategoryResponse(val category: Category, val products: List<Product>)