package br.app.pi4mobile.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.app.pi4mobile.R
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.models.Category
import br.app.pi4mobile.models.Photo
import br.app.pi4mobile.models.Product
import br.app.pi4mobile.models.response.ProductResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.card_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable


class CustomAdapter(
    private var product: List<Product>
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    private val items: MutableList<CardView>

    init {
        this.items = ArrayList()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val ivImage: ImageView = itemView.iv_image
        val tvName: TextView = itemView.tvNameProduct
        val tvPrice: TextView = itemView.tvPrice
        val card: CardView = itemView.card

        init{
            itemView.setOnClickListener { v: View ->
                val position = adapterPosition
                RetrofitClient.instance.getProduct(id = product[position].id)
                    .enqueue(object: Callback<ProductResponse>{
                        override fun onResponse(
                            call: Call<ProductResponse>,
                            response: Response<ProductResponse>
                        ) {
                            val photos:List<Photo> = response.body()!!.photos
                            val categories:List<Category> = response.body()!!.categories
                            val productReturn = response.body()!!.product
                            val intent = Intent(v.context, br.app.pi4mobile.activitys.ProductActivity::class.java)
                            intent.putExtra("product", productReturn)
                            intent.putExtra("categories", categories as Serializable)
                            intent.putExtra("photos", photos as Serializable)
                            v.context.startActivity(intent)
                        }

                        override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                            Toast.makeText(v.context, "Não foi encontrado este produto", Toast.LENGTH_SHORT).show()
                        }

                    })

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = product[position].name
        holder.tvPrice.text = product[position].price
        Picasso.get().load("""http://10.0.2.2:8000/storage/${product[position]?.photos?.get(0)?.image}""").into(holder.ivImage)
        items.add(holder.card)
    }

    override fun getItemCount(): Int {
        return product.size
    }
}