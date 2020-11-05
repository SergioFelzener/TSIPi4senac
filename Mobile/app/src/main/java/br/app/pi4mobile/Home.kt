package br.app.pi4mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.app.pi4mobile.adapters.CustomAdapter
import br.app.pi4mobile.models.Product
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Toast.makeText(this, "Bem-Vindo", Toast.LENGTH_SHORT).show()

        val product : MutableList<Product> = ArrayList()
        for(i in 1..10)
            product.add(Product("Title $i"))

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adapter = CustomAdapter(product)

        rvList.layoutManager = layoutManager
        rvList.setHasFixedSize(true)
        rvList.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
       return true
    }
}