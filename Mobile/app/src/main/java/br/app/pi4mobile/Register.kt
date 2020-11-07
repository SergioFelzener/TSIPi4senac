package br.app.pi4mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.app.pi4mobile.api.RetrofitInitializer
import br.app.pi4mobile.api.UserModel
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

       registerButton.setOnClickListener {

           val userName = nameRegisterInput.text.toString()
           val userEmail = emailRegisterInput.text.toString()
           val userPassword = passwordRegisterInput.text.toString()


           val user = JSONObject()

           user.put("name", userName)
           user.put("email", userEmail)
           user.put("password", userPassword)

           val call: Call<UserModel> = RetrofitInitializer().userService().registerUser(user)

           call.enqueue(object: Callback<UserModel> {
               override fun onFailure(call: Call<UserModel>, t: Throwable) {

                   apiResponse.text = t.message.toString()
               }

               override fun onResponse(
                   call: Call<UserModel>,
                   response: Response<UserModel>
               ) {

                   apiResponse.text = response.toString()

                   //val user: UserModel = response.body()!!


               }

           })
       }
    }
}