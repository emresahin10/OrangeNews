package com.denbase.orangenews.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.denbase.orangenews.repositories.AuthRepository
import com.denbase.orangenews.utils.Resource
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    application: Application,
    private val repository: AuthRepository
): AndroidViewModel(application) {

    private var _signupStatus = MutableLiveData<Resource<AuthResult>>()
    var signupStatus: LiveData<Resource<AuthResult>> = _signupStatus


    private var _loginStatus = MutableLiveData<Resource<AuthResult>>()
    var loginStatus: LiveData<Resource<AuthResult>> = _loginStatus



    fun signupWithMail(fullName: String, mail: String, password: String){
        val errorMsg = if (fullName.isEmpty() || mail.isEmpty() || password.isEmpty()) "Please fill all blanks" else null

        errorMsg?.let {
            _signupStatus.postValue(Resource.Error(it))
            return
        }

        _signupStatus.postValue(Resource.Loading())

        viewModelScope.launch {
            val response = repository.signUpWithMail(fullName, mail, password)
            _signupStatus.value = response
        }
    }

    fun loginWithMail(mail: String, password: String){
        val errorMsg = if (mail.isEmpty() || password.isEmpty()) "Please fill all blanks" else null

        errorMsg?.let {
            _loginStatus.postValue(Resource.Error(it))
            return
        }

        _signupStatus.postValue(Resource.Loading())

        viewModelScope.launch {
            val response = repository.loginWithMail(mail, password)
            _loginStatus.value = response
        }
    }

}