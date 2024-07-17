package com.ccspart2.projectcerberusadmincompose.utils

object InputValidator {

    fun isValidPhoneNumber(phone: String): Boolean {
        if (phone.isEmpty()) return false
        val phoneRegex = Regex("^(\\d{10}|\\(\\d{3}\\)\\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4})$")
        return phone.matches(phoneRegex)
    }

    fun isValidEmail(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        return email.matches(emailRegex)
    }

    fun isValidName(name: String): Boolean {
        if (name.isEmpty()) return false
        val nameRegex = Regex("^[A-Za-z]+( [A-Za-z]+)*$")
        return name.matches(nameRegex)
    }
}
