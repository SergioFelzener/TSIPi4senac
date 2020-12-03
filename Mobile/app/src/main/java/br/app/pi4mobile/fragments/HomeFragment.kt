package br.app.pi4mobile.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import br.app.pi4mobile.R
import br.app.pi4mobile.adapters.CustomAdapter
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.models.Photo
import br.app.pi4mobile.models.Product
import br.app.pi4mobile.models.response.ProductsResponse
import br.app.pi4mobile.models.response.SearchResponse
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {

                    getProducts(query)
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {

                    getProducts(newText)
                }
                return false
            }
        })
        RetrofitClient.instance.getProducts()
            .enqueue(object : Callback<ProductsResponse>{
                override fun onResponse(
                    call: Call<ProductsResponse>,
                    response: Response<ProductsResponse>
                ) {

                    val Products:List<Product> = response.body()!!.products
                    val product : MutableList<Product> = ArrayList()
                    for(p in Products){
                        product.add(p)
                    }
                    val adapter = CustomAdapter(product)
                    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    rvList.layoutManager = layoutManager
                    rvList.setHasFixedSize(true)
                    rvList.adapter = adapter
                }

                override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
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

    fun getProducts(key: String){
        RetrofitClient.instance.search_product(key)
            .enqueue(object : Callback<SearchResponse>{
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {

                    val Products: List<Product>? = response.body()?.products
                    val product : MutableList<Product> = ArrayList()
                    if (Products != null) {
                        for(p in Products){
                            product.add(p)
                        }
                    }
                    val adapter = CustomAdapter(product)
                    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    rvList.layoutManager = layoutManager
                    rvList.setHasFixedSize(true)
                    rvList.adapter = adapter
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Log.e("ERROR", t.message.toString())
                }


            })
    }

}