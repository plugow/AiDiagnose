package plugow.aidiagnose.network

import io.reactivex.Single
import plugow.aidiagnose.model.Doctor
import plugow.aidiagnose.model.Specialization
import plugow.aidiagnose.model.Symptom
import plugow.aidiagnose.model.Visit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface api {

    @GET("symptom")
    fun getSymptoms(@Header("Authorization") token: String?): Single<Response<List<Symptom>>>

    @GET("specialization")
    fun getSpecializations(@Header("Authorization") token: String?): Single<Response<List<Specialization>>>

    @GET("getDoctors")
    fun getDoctors(@Header("Authorization") token: String?): Single<Response<List<Doctor>>>

    @GET("getVisits")
    fun getVisits(@Header("Authorization") token: String?): Single<Response<List<Visit>>>
}