package com.example.cinedrivein.data.repository

import com.example.cinedrivein.data.remote.service.firestore.FirestoreService
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.model.user.User
import com.example.cinedrivein.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser

class UserRepositoryImpl(private val auth: FirebaseAuth, private val firestoreService: FirestoreService): UserRepository {
    override suspend fun login(email: String, password: String, onHandler: (Request) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            when{
                task.isSuccessful -> onHandler(Request.Success(data = task.result.user))
                else -> {
                    val message = when(task.exception){
                        is FirebaseAuthInvalidUserException -> "Não foi encontrado um usuário com esse e-mail"
                        is FirebaseAuthInvalidCredentialsException -> "E-mail ou senha incorretos. Verifique os dados inseridos!"
                        else -> "Algo inesperado ocorreu. Tente novamente!"
                    }

                    onHandler(Request.Error(data = task.exception, message = message))
                }
            }
        }
    }

    override suspend fun checkUser(onHandler: (FirebaseUser?) -> Unit) {
        onHandler(auth.currentUser)
    }

    override suspend fun logout(onHandler: (FirebaseUser?) -> Unit) {
        auth.signOut()
        onHandler(auth.currentUser)
    }

    override suspend fun getUser(userUid: String, onHandler: (Request) -> Unit) {
        firestoreService.getUser(userUid = userUid){
            onHandler(it)
        }
    }

    override suspend fun recoverPassword(email: String, onHandler: (Request) -> Unit) {
       auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
           when{
               task.isSuccessful -> onHandler(Request.Success(data = true))
               else -> onHandler(Request.Error(data = false, message = "Não foi possível enviar o e-email. Tente novamente!"))
           }
       }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        ancineNumber: String,
        onHandler: (Request) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            when{
                task.isSuccessful -> onHandler(Request.Success(data = task.result.user))
                else -> onHandler(Request.Error(data = task.exception, message = task.exception?.message))
            }
        }
    }

    override suspend fun createUser(
        user: User,
        onHandler: (Request) -> Unit
    ) {
        firestoreService.createUser(user = user, onHandler = onHandler)
    }
}