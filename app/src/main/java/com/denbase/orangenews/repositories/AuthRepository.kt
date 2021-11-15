package com.denbase.orangenews.repositories

import android.content.Context
import com.denbase.orangenews.utils.Resource
import com.google.firebase.auth.AuthResult

interface AuthRepository {

    suspend fun signUpWithMail(fullName: String, mail:String, password: String, context: Context?): Resource<AuthResult>

    suspend fun loginWithMail(mail: String, password: String, context: Context?): Resource<AuthResult>

    suspend fun resetPassword(mail: String, context: Context?): Resource<Void>

}