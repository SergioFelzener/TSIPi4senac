package br.app.pi4mobile.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.app.pi4mobile.R
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.models.Product
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {


        RetrofitClient.instance.getCartItems()
            .enqueue(object : Callback<List<Product>> {
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {

                    val Products:List<Product> = response.body()!!

                    for(p in Products){

                        val card = layoutInflater.inflate(R.layout.cart_item, cartItemsContainer, false)

                        card.cartItemName.text = p.name
                        card.cartItemPrice.text = p.price
                        card.cartItemQuantity.text = p.quantity

                        card.cartItemDeleteIcon.setOnClickListener {

                            // remove-prod/{id} -> p.id

                        }


                        cartItemsContainer.addView(card)



                    }


                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    Log.e("ERROR", t.message.toString())
                }


            })
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