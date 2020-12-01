    package br.app.pi4mobile.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.app.pi4mobile.R
import br.app.pi4mobile.activitys.OrderActivity
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.models.Order
import br.app.pi4mobile.models.Product
import br.app.pi4mobile.models.response.OrderResponse
import br.app.pi4mobile.models.response.OrdersResponse
import br.app.pi4mobile.storage.SharedPrefManager
import kotlinx.android.synthetic.main.card_orders.view.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_orders.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrdersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrdersFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val shared = SharedPrefManager.getInstance(requireContext())

        RetrofitClient.AUTH = """Bearer ${shared.user.token}"""
        RetrofitClient.instance.getOrders()
            .enqueue(object : Callback<OrdersResponse>{
                override fun onResponse(
                    call: Call<OrdersResponse>,
                    response: Response<OrdersResponse>
                ) {
                    val orders: List<Order>? = response.body()?.orders

                    if (orders != null) {
                        tvMessageOrders.visibility = View.GONE
                        for (o in orders){
                            var card = this@OrdersFragment.layoutInflater.inflate(
                                R.layout.card_orders,
                                ordersItemsContainer,
                                false
                            )

                            card.orderItemPedido.text = o.id.toString().trim()
                            card.orderItemTotal.text = """R$ ${o.total}"""
                            card.setOnClickListener {

                                RetrofitClient.instance.getOrder(id = o.id)
                                    .enqueue(object: Callback<OrderResponse>{
                                        override fun onResponse(
                                            call: Call<OrderResponse>,
                                            response: Response<OrderResponse>
                                        ) {

                                            val prices: ArrayList<String>? = response.body()?.price
                                            val amounts: ArrayList<Int>? = response.body()?.amount
                                            val products: List<Product>? = response.body()?.products
                                            val total: String? = response.body()?.total
                                            val intent = Intent(context, OrderActivity::class.java)
                                            intent.putExtra("products", products as Serializable)
                                            intent.putExtra("prices", prices)
                                            intent.putExtra("amounts", amounts)
                                            intent.putExtra("total", total)
                                            intent.putExtra("id", o.id.toString().trim())
                                            context?.startActivity(intent)
                                        }

                                        override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                                            Toast.makeText(context, "Não foi encontrado este produto", Toast.LENGTH_SHORT).show()
                                        }

                                    })
                            }
                            ordersItemsContainer.addView(card)
                        }
                    }
                }

                override fun onFailure(call: Call<OrdersResponse>, t: Throwable) {
                    Log.e("ERROR", t.message.toString())
                    Toast.makeText(context, "Não ", Toast.LENGTH_SHORT).show()
                }

            })






    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }
}