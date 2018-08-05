package plugow.aidiagnose.model

import com.google.gson.annotations.SerializedName

data class Symptom(@SerializedName("id") val id:Int, @SerializedName("name") val name:String)