package com.ivano.uas_native

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Cerita(val index:Int,val judul:String, val penulis:String, val desc:String, val foto:String, val access:String, val genre:String)
    : Parcelable{
        override fun toString()=judul
    }
