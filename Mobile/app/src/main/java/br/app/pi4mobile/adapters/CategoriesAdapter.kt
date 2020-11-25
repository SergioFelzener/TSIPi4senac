package br.app.pi4mobile.adapters

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import br.app.pi4mobile.R
import br.app.pi4mobile.activitys.CategoryActivity
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.models.Category
import br.app.pi4mobile.models.Photo
import br.app.pi4mobile.models.Product
import br.app.pi4mobile.models.response.CategoryResponse
import kotlinx.android.synthetic.main.card_category.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class CategoriesAdapter(var context: Context, var categories: List<Category>): BaseAdapter() {
    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(p0: Int): Any {
        return categories.get(p0)
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view: View = View.inflate(context, R.layout.card_category, null)
        var nameCategory: TextView = view.nameCategory
        var descCategory: TextView = view.descCategory

        var listItem: Category = categories.get(position)

        nameCategory.text = listItem.name
        descCategory.text = listItem.description
        view.setOnClickListener { v: View ->
            val position = position
            RetrofitClient.instance.getCategory(id = listItem.id)
                .enqueue(object: Callback<CategoryResponse> {
                    override fun onResponse(
                        call: Call<CategoryResponse>,
                        response: Response<CategoryResponse>
                    ) {

                        if(response.body() != null) {
                            val intent = Intent(v.context, CategoryActivity::class.java)
                            val category: Category = response.body()!!.category
                            val products: List<Product> = response.body()!!.products

                            if (category.description != null) {
                                intent.putExtra("category", category)
                                intent.putExtra("products", products as Serializable)
                            } else {
                                intent.putExtra("category_name", category.name)
                                intent.putExtra("products", products as Serializable)
                            }




                            v.context.startActivity(intent)
                        }



                    }

                    override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                        Toast.makeText(v.context, "Não foi encontrado esta categoria", Toast.LENGTH_SHORT).show()
                    }

                })

        }
        return view
    }
}