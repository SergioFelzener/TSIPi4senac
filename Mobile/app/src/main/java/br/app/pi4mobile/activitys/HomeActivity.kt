package br.app.pi4mobile.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import br.app.pi4mobile.R
import br.app.pi4mobile.fragments.*
import br.app.pi4mobile.storage.SharedPrefManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    lateinit var profileFragment: ProfileFragment
    lateinit var categoriesFragment: CategoriesFragment
    lateinit var cartFragment: CartFragment
    lateinit var homeFragment: HomeFragment
    lateinit var ordersFragment: OrdersFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val shared = SharedPrefManager.getInstance(applicationContext)
        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.btm_nav)
        bottomNavigationView.menu.clear()
        if(shared.isLoggedIn){
            bottomNavigationView.inflateMenu(R.menu.bottom_nav_islogged)
            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                when(item.itemId){
                    R.id.home ->{
                        homeFragment = HomeFragment()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, homeFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                    R.id.categories ->{
                        categoriesFragment = CategoriesFragment()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, categoriesFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                    R.id.orders ->{
                        ordersFragment = OrdersFragment()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, ordersFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                    R.id.cart ->{
                        cartFragment = CartFragment()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, cartFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                    R.id.profile ->{
                        profileFragment = ProfileFragment()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, profileFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                }

                true
            }
        }else{
            bottomNavigationView.inflateMenu(R.menu.bottom_nav)
            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                when(item.itemId){

                    R.id.home ->{
                        homeFragment = HomeFragment()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, homeFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                    R.id.categories ->{
                        categoriesFragment = CategoriesFragment()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, categoriesFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                    R.id.profile ->{
                        profileFragment = ProfileFragment()
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, profileFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                }

                true
            }
        }

        Toast.makeText(this, "Bem-Vindo", Toast.LENGTH_SHORT).show()
    }


}




