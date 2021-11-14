package com.denbase.orangenews.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.denbase.orangenews.R
import com.denbase.orangenews.databinding.FragmentLoginBinding
import com.denbase.orangenews.ui.MainActivity
import com.denbase.orangenews.utils.Resource
import com.denbase.orangenews.viewmodels.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    companion object {
        val RC_SIGN_IN = 4926
        val TAG = "GOOGLE_SIGN_IN"
    }
    //  TODO("forgot password screen need")

    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)

        binding.txtCreateAccount.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }

        login()
        firebaseAuth = Firebase.auth

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()


        val client = GoogleSignIn.getClient(requireContext(),gso)

        binding.btnGoogleLogin.setOnClickListener {
            Log.d(TAG, "begin google signin")
            val intent = client.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }

        binding.btnLogin.setOnClickListener {
            val mail = binding.txtLoginEmail.text.toString()
            val password = binding.txtLoginPassword.text.toString()

            viewModel.loginWithMail(mail, password)
        }

    }

    fun login(){
        viewModel.logintatus.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                  // TODO("need progressbar")
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

    override fun onStart() {
        super.onStart()
        var currentUser = firebaseAuth.getCurrentUser()
        updateUI(currentUser)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.d(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = firebaseAuth.currentUser
                    updateUI(user)
                } else {
                    Log.d(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null){
            Log.d(TAG, "user not signed in")
            return
        }
        startActivity(Intent(requireActivity(), MainActivity::class.java))
        activity?.finish()
    }

}