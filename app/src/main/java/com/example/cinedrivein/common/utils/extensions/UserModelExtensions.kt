package com.example.cinedrivein.common.utils.extensions

import com.example.cinedrivein.domain.model.user.User

fun User.toHash(): HashMap<String, String>{
    return hashMapOf(
        "name" to this.name,
        "email" to this.email,
        "ancineNumber" to this.ancineNumber.toString(),
        "uid" to this.uid
    )
}