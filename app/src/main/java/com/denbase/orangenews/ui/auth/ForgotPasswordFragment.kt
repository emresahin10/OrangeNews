package com.denbase.orangenews.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.denbase.orangenews.R
import com.denbase.orangenews.databinding.FragmentForgotPasswordBinding
import com.denbase.orangenews.utils.*
import com.denbase.orangenews.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPasswordBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)

        resetPassword()

        binding.btnForgotPassword.setOnClickListener {
            val mail = binding.txtForgotPasswordMail.text.toString()
            viewModel.resetPassword(mail, context)
        }
    }

    private fun resetPassword(){
        viewModel.resetPasswordStatus.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    binding.pbForgotPassword.showView()
                }
                is Resource.Success -> {
                    binding.pbForgotPassword.hideView()

                    //Alertdialog
                    requireContext().alert {
                        setTitle(getString(R.string.reset_password))
                        setMessage(getString(R.string.reset_password_msg))
                        setIcon(R.drawable.ic_logo)
                        neutralButton{
                            findNavController().navigate(ForgotPasswordFragmentDirections
                                .actionForgotPasswordFragmentToLoginFragment())
                            activity?.finish()
                        }
                    }
                }

                is Resource.Error -> {
                    binding.pbForgotPassword.hideView()
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}