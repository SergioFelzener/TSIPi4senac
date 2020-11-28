package br.app.pi4mobile.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.app.pi4mobile.R
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.models.Category
import br.app.pi4mobile.models.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        var name: String? = intent.getStringExtra("name")
        var description: String? = intent.getStringExtra("description")
        var price: String? = intent.getStringExtra("valor")
        var categories = intent.getParcelableArrayListExtra<Category>("categories")
        var photos = intent.getParcelableArrayListExtra<Photo>("photos")
        productName.text = name
        productDetails.text = description
        valorText.text = price
        tvCategory.text = categories?.get(0)!!.name
        Picasso.get().load("""http://10.0.2.2:8000/storage/${photos?.get(0)?.image}""").into(ivImage)
    }

}