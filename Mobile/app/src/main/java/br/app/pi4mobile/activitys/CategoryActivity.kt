package br.app.pi4mobile.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.app.pi4mobile.R
import br.app.pi4mobile.adapters.CustomAdapter
import br.app.pi4mobile.models.Category
import br.app.pi4mobile.models.Product
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)


        val category = intent.getParcelableExtra<Category>("category")
        val products = intent.getParcelableArrayListExtra<Product>("products")

        if(category != null){

            tvName.text = category.toString()
            if (category != null) {
                if (category.name != null) {
                    tvName.text = category.name
                }
                if (category.description != null) {
                    tvDesc.text = category.description
                }
            }
        }else{
            val nameCategory = intent.getStringExtra("category_name")
            tvName.text = nameCategory
            tvDesc.visibility = View.GONE
        }

        if(products != null){
            val product : MutableList<Product> = ArrayList()
            for(p in products!!){
                product.add(p)
            }

            val adapter = CustomAdapter(product)
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            this.rvList.layoutManager = layoutManager
            this.rvList.setHasFixedSize(true)
            this.rvList.adapter = adapter
        }



    }
}