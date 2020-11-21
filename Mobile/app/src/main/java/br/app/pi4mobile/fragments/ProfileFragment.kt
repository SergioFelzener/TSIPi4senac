package br.app.pi4mobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import br.app.pi4mobile.R
import br.app.pi4mobile.storage.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        val shared = SharedPrefManager.getInstance(requireContext())
        if(shared.isLoggedIn){

            btnLogout.setOnClickListener {
                shared.clear()
            }
        }else{
            btnLogout.text = "opa"
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