package br.app.pi4mobile.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.app.pi4mobile.R
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        var name: String? = intent.getStringExtra("name")
        var description: String? = intent.getStringExtra("description")
        var price: String? = intent.getStringExtra("valor")
        productName.text = name
        productDetails.text = description
        valorText.text = price
        ivImage.setImageResource(R.drawable.ic_action_profile)
    }

}