package com.ivano.uas_native
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date
@Parcelize
data class Paragraf(val id:Number,val story_teller:Number,val paragraf : String, val name:String, val id_cerita : Number, val created_date:String):Parcelable{

}
