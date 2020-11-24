package br.app.pi4mobile.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.app.pi4mobile.R
import br.app.pi4mobile.models.Category
import br.app.pi4mobile.models.Photo
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        var category = intent.getParcelableExtra<Category>("category")
        if (category != null) {
            tvName.text = category.name
            tvDesc.text = category.description
        }

    }
}