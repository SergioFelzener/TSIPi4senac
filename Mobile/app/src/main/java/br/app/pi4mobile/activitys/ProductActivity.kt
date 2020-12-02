package br.app.pi4mobile.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import br.app.pi4mobile.R
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.fragments.CartFragment
import br.app.pi4mobile.fragments.HomeFragment
import br.app.pi4mobile.models.Category
import br.app.pi4mobile.models.Photo
import br.app.pi4mobile.models.Product
import br.app.pi4mobile.models.response.AddProdResponse
import br.app.pi4mobile.storage.SharedPrefManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    lateinit var cartFragment: CartFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val shared = SharedPrefManager.getInstance(applicationContext)

        RetrofitClient.AUTH = """Bearer ${shared.user.token}"""
        var product: Product? = intent.getParcelableExtra<Product>("product")
        var categories = intent.getParcelableArrayListExtra<Category>("categories")
        var photos = intent.getParcelableArrayListExtra<Photo>("photos")
        productName.text = product?.name
        productDetails.text = product?.description
        valorText.text = """R$ ${product?.price} """
        Picasso.get().load("""http://10.0.2.2:8000/storage/${photos?.get(0)?.image}""").into(ivImage)

        btnComprar.setOnClickListener {
            if(!shared.isLoggedIn){
                val i = Intent(applicationContext, LoginActivity::class.java)
                startActivity(i)
            }else{
                RetrofitClient.instance.addProductCart(product_id = product?.id, amount = 1)
                    .enqueue(object : Callback<AddProdResponse>{
                        override fun onResponse(call: Call<AddProdResponse>, response: Response<AddProdResponse>) {
                            Toast.makeText(applicationContext, response.body()?.success, Toast.LENGTH_SHORT).show()
                            val fragment = HomeFragment()
                            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
                        }
                        override fun onFailure(call: Call<AddProdResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }
    }

}