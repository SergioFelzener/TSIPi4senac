package br.aulas.pi4mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
       return true
    }
}