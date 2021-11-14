package com.denbase.orangenews.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.denbase.orangenews.R
import com.denbase.orangenews.databinding.FragmentSignUpBinding
import com.denbase.orangenews.ui.MainActivity
import com.denbase.orangenews.utils.Resource
import com.denbase.orangenews.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)

        signUp()
        login()

        binding.btnSignup.setOnClickListener {
            viewModel.signupWithMail(
                fullName = binding.txtSignupFullName.text.toString(),
                mail = binding.txtSignupMail.text.toString(),
                password = binding.txtSignupPassword.text.toString()
            )
        }
    }

    fun signUp(){
        viewModel.signupStatus.observe(viewLifecycleOwner, Observer {
            when (it){
                is Resource.Loading -> {
                    //TODO("progressbar neeed")
                }
                is Resource.Success -> {
                    val mail = binding.txtSignupMail.text.toString()
                    val password = binding.txtSignupPassword.text.toString()
                    viewModel.loginWithMail(mail, password)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(),it.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun login(){
        viewModel.logintatus.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    //TODO("progressbar neeed")
                }
                is Resource.Success -> {
                    startActivity(Intent(requireActivity(), MainActivity::class.java))
                    activity?.finish()
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(),it.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}