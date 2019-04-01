package plugow.aidiagnose.model

import com.google.gson.annotations.SerializedName

class Doctor(@SerializedName("id") val id:Int,
                  @SerializedName("firstName") val firstName:String,
                  @SerializedName("lastName") val lastName:String,
                  @SerializedName("latitude") val latitude:Double,
                  @SerializedName("longitude") val longitude:Double)