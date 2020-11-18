package br.app.pi4mobile.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.app.pi4mobile.R
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.models.LoginResponse
import br.app.pi4mobile.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btnLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPasswordLogin.text.toString().trim()

            if(email.isEmpty()){
                etEmail.error = "Email é obrigatório"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                etPasswordLogin.error = "Senha é obrigatório"
                etPasswordLogin.requestFocus()
                return@setOnClickListener
            }
            RetrofitClient.instance.login(email, password,  "app")
                .enqueue(object: Callback<LoginResponse>{
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {

                            SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.user!!)
                            Toast.makeText(applicationContext, "Login efetuado com sucesso!", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@Login, Home::class.java))

                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, "Email ou senha estão incorretos!", Toast.LENGTH_LONG).show()
                    }

                })
        }
    }

    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, Home::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }

}