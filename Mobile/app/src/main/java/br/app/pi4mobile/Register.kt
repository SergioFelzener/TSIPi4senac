package br.app.pi4mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.app.pi4mobile.api.RetrofitInitializer
import br.app.pi4mobile.api.UserModel
import br.app.pi4mobile.api.UserService
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

       registerButton.setOnClickListener {

           val userName = nameRegisterInput.text
           val userEmail = emailRegisterInput.text
           val userPassword = passwordRegisterInput.text

           val userToRegister = UserModel( userName.toString(),  userEmail.toString(), userPassword.toString())

           val retrofit = Retrofit.Builder()
               .baseUrl("http://10.0.2.2:8000/api")
               .addConverterFactory(GsonConverterFactory.create())
               .build()

           val service = retrofit.create(UserService::class.java)

           val call = service.registerUser(userToRegister)

            val callback = object: Callback<UserModel> {
                override fun onFailure(call: Call<UserModel>, t: Throwable) {

                    Toast.makeText(this@Register, "Algo de errado não está certo", Toast.LENGTH_LONG).show()

                    Log.e("Register", "registerUser", t)

                }

                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {

                    if(response.isSuccessful) {


                        val user = response.body()
                        Toast.makeText(this@Register, user.toString(), Toast.LENGTH_LONG).show()

                    } else {

                        Toast.makeText(this@Register, "Algo de errado continua errado", Toast.LENGTH_LONG).show()
                    }

                }


            }

           call.enqueue(callback)

       }


    }
}