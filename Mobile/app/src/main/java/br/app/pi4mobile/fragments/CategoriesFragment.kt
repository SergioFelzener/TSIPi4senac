package br.app.pi4mobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import br.app.pi4mobile.R
import br.app.pi4mobile.adapters.CategoriesAdapter
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.models.Category
import br.app.pi4mobile.models.response.DefaultResponse
import kotlinx.android.synthetic.main.fragment_categories.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {

    private var arrayList:ArrayList<Category> ? = null
    private var gridView: GridView? = null
    private var categoriesAdapter: CategoriesAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        RetrofitClient.instance.getCategories()
            .enqueue(object : Callback<List<Category>> {
                override fun onResponse(
                    call: Call<List<Category>>,
                    response: Response<List<Category>>
                ) {
                    val categories: List<Category> = response.body()!!
                    val category: ArrayList<Category> = ArrayList()
                    for (c in categories) {
                        category.add(c)
                    }
                    categoriesAdapter = CategoriesAdapter(requireContext(), category)
                    this@CategoriesFragment.grid_categories.adapter = categoriesAdapter

                }

                override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                    Toast.makeText(context, "Deu erro!", Toast.LENGTH_LONG)
                }

            })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }
}