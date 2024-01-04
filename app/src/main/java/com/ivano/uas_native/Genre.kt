package com.ivano.uas_native

data class Genre(
    var id : Int,
    var name : String
){
    override fun toString(): String {
        return name
    }
}
