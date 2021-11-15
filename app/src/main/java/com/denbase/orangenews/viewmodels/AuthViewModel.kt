package com.denbase.orangenews.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.denbase.orangenews.R
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

    private var _resetPasswordStatus = MutableLiveData<Resource<Void>>()
    var resetPasswordStatus: LiveData<Resource<Void>> = _resetPasswordStatus



    fun signupWithMail(fullName: String, mail: String, password: String, context: Context?){
        val errorMsg = if (fullName.isEmpty() || mail.isEmpty() || password.isEmpty())
            context?.getString(R.string.empty_fields)  else null

        errorMsg?.let {
            _signupStatus.postValue(Resource.Error(it))
            return
        }

        _signupStatus.postValue(Resource.Loading())

        viewModelScope.launch {
            val response = repository.signUpWithMail(fullName, mail, password, context)
            _signupStatus.value = response
        }
    }

    fun loginWithMail(mail: String, password: String, context: Context?){
        val errorMsg = if (mail.isEmpty() || password.isEmpty())
            context?.getString(R.string.empty_fields)  else null

        errorMsg?.let {
            _loginStatus.postValue(Resource.Error(it))
            return
        }
        _signupStatus.postValue(Resource.Loading())

        viewModelScope.launch {
            val response = repository.loginWithMail(mail, password, context)
            _loginStatus.value = response
        }
    }

    fun resetPassword(mail: String, context: Context?){
        val errorMsg = if (mail.isEmpty()) "You can't empty mail address" else null

        errorMsg?.let {
            _resetPasswordStatus.postValue(Resource.Error(it))
            return
        }
        _resetPasswordStatus.postValue(Resource.Loading())

        viewModelScope.launch {
            val response = repository.resetPassword(mail, context)
            _resetPasswordStatus.value = response
        }
    }

}