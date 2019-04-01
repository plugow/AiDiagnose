package plugow.aidiagnose.network

import io.reactivex.Single
import okhttp3.RequestBody
import plugow.aidiagnose.model.*
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @GET("symptom")
    fun getSymptoms(@Header("Authorization") token: String?): Single<Response<List<Symptom>>>

    @GET("specialization")
    fun getSpecializations(@Header("Authorization") token: String?): Single<Response<List<Specialization>>>

    @GET("getDoctors")
    fun getDoctors(@Header("Authorization") token: String?): Single<Response<List<Doctor>>>

    @GET("getVisits")
    fun getVisits(@Header("Authorization") token: String?): Single<Response<List<Visit>>>

    @GET("getVisitsForDoctor")
    fun getVisitsForDoctor(@Header("Authorization") token: String?): Single<Response<List<Visit>>>

    @FormUrlEncoded
    @POST("auth/login")
    fun loginUser(@Field("email") email: String, @Field("password") password:String): Single<Response<User>>
}