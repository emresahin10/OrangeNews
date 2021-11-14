package com.denbase.orangenews.repositories

import com.denbase.orangenews.utils.Resource
import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun signUpWithMail(fullName: String, mail:String, password: String): Resource<AuthResult>

    suspend fun loginWithMail(mail: String, password: String): Resource<AuthResult>

}