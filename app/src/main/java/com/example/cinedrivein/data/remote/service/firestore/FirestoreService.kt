package com.example.cinedrivein.data.remote.service.firestore

import android.util.Log
import com.example.cinedrivein.common.constants.FirestoreCollections
import com.example.cinedrivein.common.utils.extensions.toHash
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.model.user.User
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreService(private val firestore: FirebaseFirestore) {
    fun createUser(user: User, onHandler: (Request) -> Unit){
        firestore.collection(FirestoreCollections.UserCollection)
            .document(user.uid)
            .set(user.toHash())
            .addOnSuccessListener { document ->
                onHandler(Request.Success(data = null))
            }
            .addOnFailureListener { exception ->
                onHandler(Request.Error(data = exception, message = exception.message))
            }
    }

    fun getUser(userUid: String, onHandler: (Request) -> Unit){
        firestore.collection(FirestoreCollections.UserCollection)
            .document(userUid)
            .get()
            .addOnSuccessListener { document ->
                val ancineNumber = document.data?.get("ancineNumber") as String
                onHandler(
                    Request.Success(
                        data = User(
                            name = document.data?.get("name") as String,
                            email = document.data?.get("email") as String,
                            ancineNumber = ancineNumber.toInt(),
                            uid = document.data?.get("uid") as String
                        )
                    )
                )
            }
            .addOnFailureListener {
                onHandler(Request.Error(data = it, message = it.message))
            }
    }
}