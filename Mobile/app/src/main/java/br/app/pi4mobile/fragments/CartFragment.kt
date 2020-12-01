package br.app.pi4mobile.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import br.app.pi4mobile.R
import br.app.pi4mobile.api.ProductModel
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.models.response.AddProdResponse
import br.app.pi4mobile.models.response.CartResponse
import br.app.pi4mobile.models.response.CheckoutResponse
import br.app.pi4mobile.models.response.RemoveProdResponse
import br.app.pi4mobile.storage.SharedPrefManager
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        lateinit var ordersFragment: OrdersFragment
        val shared = SharedPrefManager.getInstance(requireContext())
        var total = 0.0
            RetrofitClient.AUTH = """Bearer ${shared.user.token}"""
            RetrofitClient.instance.getCartItems()
                .enqueue(object : Callback<CartResponse> {
                    override fun onResponse(
                        call: Call<CartResponse>,
                        response: Response<CartResponse>
                    ) {
                        val products: List<ProductModel>? = response.body()?.products
                        if (products != null) {
                            tvMessage.visibility = View.GONE
                            for (p in products) {

                                var card = this@CartFragment.layoutInflater.inflate(
                                    R.layout.cart_item,
                                    cartItemsContainer,
                                    false
                                )

                                card.cartItemName.text = p.name
                                card.cartItemPrice.text = p.price
                                card.cartItemQuantity.text = p.amount.toString().trim()
                                total += p.price!!.toDouble()!! * p.amount!!
                                tvTotalCart.text = """R$ $total"""
                                card.cartItemDeleteIcon.setOnClickListener {
                                    RetrofitClient.instance.removeProdOne(product_id = p.id)
                                        .enqueue(object : Callback<RemoveProdResponse> {
                                            override fun onResponse(
                                                call: Call<RemoveProdResponse>, response: Response<RemoveProdResponse>) {
                                                Toast.makeText(context, response.body()?.success, Toast.LENGTH_SHORT).show()
                                                val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
                                                ft.detach(this@CartFragment).attach(this@CartFragment).commit()
                                            }
                                            override fun onFailure(call: Call<RemoveProdResponse>, t: Throwable) {
                                                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                                            }
                                        })
                                }
                                card.cartItemRemoveAll.setOnClickListener {
                                    RetrofitClient.instance.removeProd(product_id = p.id)
                                        .enqueue(object : Callback<RemoveProdResponse> {
                                            override fun onResponse(call: Call<RemoveProdResponse>, response: Response<RemoveProdResponse>) {
                                                Toast.makeText(context, response.body()?.success, Toast.LENGTH_SHORT).show()
                                                val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
                                                ft.detach(this@CartFragment).attach(this@CartFragment).commit()
                                            }
                                            override fun onFailure(call: Call<RemoveProdResponse>, t: Throwable) {
                                                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                                            }
                                        })
                                }
                                card.cartAddItemIcon.setOnClickListener {
                                    RetrofitClient.instance.addProductCart(product_id = p.id, amount = 1)
                                        .enqueue(object : Callback<AddProdResponse>{
                                            override fun onResponse(
                                                call: Call<AddProdResponse>,
                                                response: Response<AddProdResponse>
                                            ) {
                                                Toast.makeText(context, response.body()?.success, Toast.LENGTH_SHORT).show()
                                                val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
                                                ft.detach(this@CartFragment).attach(this@CartFragment).commit()
                                            }

                                            override fun onFailure(call: Call<AddProdResponse>, t: Throwable) {
                                                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                                            }


                                        })
                                }
                                this@CartFragment.cartItemsContainer.addView(card)
                            }
                        }else{
                            tvCartTotal.visibility = View.GONE
                            checkoutBtn.visibility = View.GONE
                        }
                    }
                    override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                        Log.e("ERROR", t.message.toString())
                    }


                })

        checkoutBtn.setOnClickListener {
            val total = tvTotalCart.text.toString().split(" ")
            RetrofitClient.AUTH = """Bearer ${shared.user.token}"""
            RetrofitClient.instance.checkout(total = total[1].toDouble())
                .enqueue(object : Callback<CheckoutResponse>{
                    override fun onResponse(
                        call: Call<CheckoutResponse>,
                        response: Response<CheckoutResponse>
                    ) {
                        Toast.makeText(context, response.body()?.success, Toast.LENGTH_LONG).show()
                        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
                        ft.detach(this@CartFragment).attach(ordersFragment).commit()
                    }

                    override fun onFailure(call: Call<CheckoutResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                })
        }
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)



    }
}