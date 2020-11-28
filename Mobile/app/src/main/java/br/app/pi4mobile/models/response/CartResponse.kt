package br.app.pi4mobile.models.response

import br.app.pi4mobile.api.ProductModel
import br.app.pi4mobile.models.Product


data class CartResponse(val products: List<ProductModel>)