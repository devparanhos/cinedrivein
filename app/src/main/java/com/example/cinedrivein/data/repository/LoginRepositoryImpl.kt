package com.example.cinedrivein.data.repository

import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser

class LoginRepositoryImpl(private val auth: FirebaseAuth): LoginRepository {
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
}