package com.example.cinedrivein.data.remote.service.firestore

import android.util.Log
import com.example.cinedrivein.common.constants.FirestoreCollections
import com.example.cinedrivein.common.utils.extensions.toHash
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.model.distributor.Distributor
import com.example.cinedrivein.domain.model.user.User
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreService(private val firestore: FirebaseFirestore) {
    fun createUser(user: User, onHandler: (Request) -> Unit){
        firestore.collection(FirestoreCollections.USERS_COLLECTION)
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
        firestore.collection(FirestoreCollections.USERS_COLLECTION)
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

    fun getDistributors(onHandler: (Request) -> Unit){
        firestore.collection(FirestoreCollections.DISTRIBUTORS_COLLECTION)
            .get()
            .addOnSuccessListener { document ->
                val distributors = mutableListOf<Distributor>()
                document.documents.forEach {  distributor ->
                    distributors.add(
                        Distributor(
                            name = distributor.data?.get("name") as String,
                            socialName = distributor.data?.get("socialName") as String,
                            cnpj = distributor.data?.get("cnpj") as String,
                            id = distributor.id
                        )
                    )
                }
                onHandler(Request.Success(data = distributors))
            }
            .addOnFailureListener {
                Log.i("Teste", "Teste")
            }
    }

    fun deleteDistributors(reference: String, onHandler: (Request) -> Unit){
        firestore.collection(FirestoreCollections.DISTRIBUTORS_COLLECTION)
            .document(reference)
            .delete()
            .addOnSuccessListener {
                onHandler(Request.Success(data = true))
            }
            .addOnFailureListener {
                onHandler(Request.Error(data = false, message = null))
            }
    }

    fun createDistributors(distributor: Distributor, onHandler: (Request) -> Unit){
        val distributorDoc = firestore.collection(FirestoreCollections.DISTRIBUTORS_COLLECTION).document()
        distributor.id = distributorDoc.id

        distributorDoc
            .set(distributor.toHash())
            .addOnSuccessListener {
                onHandler(Request.Success(data = true))
            }
            .addOnFailureListener {
                onHandler(Request.Error(data = false, message = it.message))
            }
    }
}