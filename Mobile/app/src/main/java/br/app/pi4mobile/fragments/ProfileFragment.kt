package br.app.pi4mobile.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import br.app.pi4mobile.R
import br.app.pi4mobile.activitys.LoginActivity
import br.app.pi4mobile.api.RetrofitClient
import br.app.pi4mobile.models.UpdateResponse
import br.app.pi4mobile.storage.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        val shared = SharedPrefManager.getInstance(requireContext())

        if(!shared.isLoggedIn){

            val i = Intent(context, LoginActivity::class.java)
            startActivity(i)
        }else{
            etEmailProfile.setText(shared.user.email)
            etPasswordProfile.setText(shared.user.password)
            etNameProfile.setText(shared.user.name)

            btnEditProfile.setOnClickListener {

                val name = etNameProfile.text.toString().trim()
                val email = etEmailProfile.text.toString().trim()
                val password = etPasswordProfile.text.toString().trim()

                RetrofitClient.instance.updateUser(id = shared.user.id, name = name,email = email, password = password)
                    .enqueue(object : Callback<UpdateResponse> {
                        override fun onResponse(
                            call: Call<UpdateResponse>,
                            response: Response<UpdateResponse>
                        ) {
                            shared.saveUser(response.body()!!.user)
                            Toast.makeText(context, response.body()?.message,Toast.LENGTH_LONG)
                        }

                        override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                            Toast.makeText(context, t.message, Toast.LENGTH_LONG)
                        }

                    })
            }
            btnLogout.setOnClickListener {
                shared.clear()
            }
        }

        super.onActivityCreated(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}