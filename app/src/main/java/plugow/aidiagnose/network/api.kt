package plugow.aidiagnose.network

import io.reactivex.Single
import okhttp3.RequestBody
import plugow.aidiagnose.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface api {

    @GET("symptom")
    fun getSymptoms(@Header("Authorization") token: String?): Single<Response<List<Symptom>>>

    @GET("specialization")
    fun getSpecializations(@Header("Authorization") token: String?): Single<Response<List<Specialization>>>

    @GET("getDoctors")
    fun getDoctors(@Header("Authorization") token: String?): Single<Response<List<Doctor>>>

    @GET("getVisits")
    fun getVisits(@Header("Authorization") token: String?): Single<Response<List<Visit>>>

    @POST("auth/login")
    fun loginUser(@Body params: RequestBody): Single<Response<User>>
}