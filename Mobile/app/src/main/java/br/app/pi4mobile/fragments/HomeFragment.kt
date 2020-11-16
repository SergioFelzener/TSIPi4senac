package br.app.pi4mobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import br.app.pi4mobile.R
import br.app.pi4mobile.adapters.CustomAdapter
import br.app.pi4mobile.models.Product
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        val product : MutableList<Product> = ArrayList()
        for(i in 1..10)
            product.add(Product("Title $i"))

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        val adapter = CustomAdapter(product)
        rvList.layoutManager = layoutManager
        rvList.setHasFixedSize(true)
        rvList.adapter = adapter

        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)


    }


}