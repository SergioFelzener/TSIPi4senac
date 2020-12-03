package br.app.pi4mobile.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.app.pi4mobile.R
import br.app.pi4mobile.models.Product
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.card_product_item.view.*


class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val products = intent.getParcelableArrayListExtra<Product>("products")
        val prices = intent.getStringArrayListExtra("prices")
        val amounts = intent.getIntegerArrayListExtra("amounts")
        val id  = intent.getStringExtra("id")
        val total = intent.getStringExtra("total")
        if (products != null) {
            for ((i, p) in products.withIndex()) {

                var card = this@OrderActivity.layoutInflater.inflate(
                    R.layout.card_product_item,
                    productItemsContainer,
                    false
                )
                tvOrder.text = """Pedido $id"""
                card.productItemName.text = p.name
                card.productItemPrice.text = prices?.get(i).toString().trim()
                card.productItemQuantity.text = amounts?.get(i).toString().trim()

                productItemsContainer.addView(card)
            }

            tvTotal.text = """R$ $total"""
        }
    }
}