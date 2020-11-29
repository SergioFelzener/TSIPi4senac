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

        if (products != null) {

            for (p in products) {

                var card = this@OrderActivity.layoutInflater.inflate(
                    R.layout.card_product_item,
                    productItemsContainer,
                    false
                )

                card.productItemName.text = p.name
                card.productItemPrice.text = prices?.get(0).toString().trim()
                card.productItemQuantity.text = amounts?.get(0).toString().trim()
                productItemsContainer.addView(card)

            }
        }
    }
}