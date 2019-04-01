package plugow.aidiagnose.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Visit(@SerializedName("id") val id:Int,
                 @SerializedName("status") val status:String,
                 @SerializedName("isRead") val isRead:Boolean,
                 @SerializedName("doctor") val doctor:String,
                 @SerializedName("patient") val patient:String,
                 @SerializedName("place") val place:String,
                 @SerializedName("date") val date:Date,
                 @SerializedName("comment") val comment:String
                 ) : Serializable