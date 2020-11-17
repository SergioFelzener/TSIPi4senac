package br.app.pi4mobile.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import br.app.pi4mobile.R
import br.app.pi4mobile.adapters.CustomAdapter
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.models.Product
import br.app.pi4mobile.models.ProductResponse
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class HomeFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {


        RetrofitClient.instance.getProducts()
            .enqueue(object : Callback<List<Product>>{
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {

                    val Products:List<Product> = response.body()!!
                    val product : MutableList<Product> = ArrayList()
                    for(p in Products){
                        product.add(p)
                    }
                    val adapter = CustomAdapter(product)
                    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    rvList.layoutManager = layoutManager
                    rvList.setHasFixedSize(true)
                    rvList.adapter = adapter
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    Log.e("ERROR", t.message.toString())
                }


            })
        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)


    }


}