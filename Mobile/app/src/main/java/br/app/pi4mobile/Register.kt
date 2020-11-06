package br.app.pi4mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.app.pi4mobile.api.RetrofitInitializer
import br.app.pi4mobile.api.UserModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

       registerButton.setOnClickListener {

           val userName = nameRegisterInput.text
           val userEmail = emailRegisterInput.text
           val userPassword = passwordRegisterInput.text

           val userToRegister: UserModel = UserModel( userName,  userEmail, userPassword)


           val call: Call<UserModel> = RetrofitInitializer().userService().loginUser((userToRegister))

           call.enqueue(object: Callback<UserModel> {
               override fun onFailure(call: Call<UserModel>, t: Throwable) {
                   Log.e("ERROR", t.message.toString())
               }

               override fun onResponse(
                   call: Call<UserModel>,
                   response: Response<UserModel>
               ) {

                   val user: UserModel = response.body()!!


                   textView3.text = user.toString()

                   Snackbar.make(
                       textView2,
                       user.toString(),
                       Snackbar.LENGTH_SHORT
                   ).show()


               }

           })
       }
    }
}