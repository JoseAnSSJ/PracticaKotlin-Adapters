package com.example.usuers

data class Usuario(val id: Long, var name: String, var lastName: String, var url: String) {


    fun getFullName(): String{
        return "$name $lastName"
    }
}