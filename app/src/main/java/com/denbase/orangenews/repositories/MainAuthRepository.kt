package com.denbase.orangenews.repositories

import com.denbase.orangenews.data.User
import com.denbase.orangenews.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class MainAuthRepository: AuthRepository {

    val auth = FirebaseAuth.getInstance()
    val users = FirebaseFirestore.getInstance().collection("users")


    override suspend fun signUpWithMail(fullName: String, mail: String, password: String): Resource<AuthResult> {
        try {
            val authResult = auth.createUserWithEmailAndPassword(mail, password).await()

           /* val user = auth.currentUser
            val uuid = user?.uid
            val dbUser = User(fullName, mail, password, uuid!!)
            users.document(uuid).set(dbUser)*/

            return Resource.Success(authResult)
        }catch (e: Exception){
            return Resource.Error(e.message)
        }
    }

    override suspend fun loginWithMail(mail: String, password: String): Resource<AuthResult> {
        try {
            var authResult = auth.signInWithEmailAndPassword(mail, password).await()

            return Resource.Success(authResult)
        }catch (e: Exception){
            return Resource.Error(e.message)
        }
    }
}