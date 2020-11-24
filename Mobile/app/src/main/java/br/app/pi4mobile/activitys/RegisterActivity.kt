package br.app.pi4mobile.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.app.pi4mobile.R
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.models.DefaultResponse
import br.app.pi4mobile.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btnLogin.setOnClickListener {

           val name = etNameRegister.text.toString().trim()
           val email = etEmailRegister.text.toString().trim()
           val password = etPasswordRegister.text.toString().trim()
           val confirmPassword = etConfirmPasswordRegister.text.toString().trim()

           if(name.isEmpty()){
               etNameRegister.error = "Nome é obrigatório"
               etNameRegister.requestFocus()
               return@setOnClickListener
           }

           if(email.isEmpty()){
               etEmailRegister.error = "Email é obrigatório"
               etEmailRegister.requestFocus()
               return@setOnClickListener
           }
           if(password.isEmpty()){
               etPasswordRegister.error = "Senha é obrigatório"
               etPasswordRegister.requestFocus()
               return@setOnClickListener
           }
           if(confirmPassword.isEmpty()){
               etConfirmPasswordRegister.error = "Confirmar senha é obrigatório"
               etConfirmPasswordRegister.requestFocus()
               return@setOnClickListener
           }

           if(password != confirmPassword){
               etPasswordRegister.error = "As senhas não coincidem"
               etPasswordRegister.requestFocus()
               return@setOnClickListener
           }

           RetrofitClient.instance.register(name, email, password)
               .enqueue(object: Callback<DefaultResponse>{
                   override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {

                        SharedPrefManager.getInstance(applicationContext).saveUser(response.body()!!.user)
                        Toast.makeText(applicationContext, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@RegisterActivity, HomeActivity::class.java))
                        
                   }

                   override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                       Toast.makeText(applicationContext, "Não possível cadastrar o usuário", Toast.LENGTH_LONG).show()
                   }

               })

       }


    }
}