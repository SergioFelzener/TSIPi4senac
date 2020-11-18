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
import br.app.pi4mobile.models.Product
import br.app.pi4mobile.models.ProductResponse
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.card_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomAdapter(
    private var product: List<Product>
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    private val items: MutableList<CardView>

    init {
        this.items = ArrayList()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val ivImage: ImageView = itemView.iv_image
        val tvName: TextView = itemView.tvName
        val tvPrice: TextView = itemView.tvPrice
        val card: CardView = itemView.card

        init{
            itemView.setOnClickListener { v: View ->
                val position = adapterPosition
                RetrofitClient.instance.getProduct(id = product[position].id)
                    .enqueue(object: Callback<Product>{
                        override fun onResponse(
                            call: Call<Product>,
                            response: Response<Product>
                        ) {
                            val productReturn = response.body()!!
                            val intent = Intent(v.context, br.app.pi4mobile.activitys.Product::class.java)
                            intent.putExtra("name", productReturn.name)
                            intent.putExtra("description", productReturn.description)
                            intent.putExtra("valor", productReturn.price)
                            v.context.startActivity(intent)
                        }

                        override fun onFailure(call: Call<Product>, t: Throwable) {
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
        holder.ivImage.setImageResource(R.drawable.ic_action_categories)

        items.add(holder.card)
    }

    override fun getItemCount(): Int {
        return product.size
    }


//    private val items: MutableList<CardView>
//
//    init {
//        this.items = ArrayList()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
//
//        return ViewHolder(v)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.tvTitle.text = product[position].name
//
//        items.add(holder.card )
//    }
//
//    override fun getItemCount(): Int { return product.size }
//
//    inner class ViewHolder
//    internal constructor(
//        itemView: View
//    ) : RecyclerView.ViewHolder(itemView){
//        val tvTitle: TextView = itemView.tvTitle
//        val card: CardView = itemView.card
//    }
}