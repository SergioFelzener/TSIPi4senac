package br.app.pi4mobile.models.response

import br.app.pi4mobile.models.Category
import br.app.pi4mobile.models.Photo
import br.app.pi4mobile.models.Product

data class ProductResponse(val product: Product, val photos: List<Photo>, val categories: List<Category>)
