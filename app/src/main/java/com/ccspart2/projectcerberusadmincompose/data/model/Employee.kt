package com.ccspart2.projectcerberusadmincompose.data.model

data class Employee(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val isAdmin: Boolean = false
)
