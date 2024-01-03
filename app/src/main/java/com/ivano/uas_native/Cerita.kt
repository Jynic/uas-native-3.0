package com.ivano.uas_native

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Cerita(val index:Number,val name:String, val judul:String, val desc:String, val foto:String, val access:String, val genre:String)
    : Parcelable{
    }
