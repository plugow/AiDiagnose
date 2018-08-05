package plugow.aidiagnose.network

import io.reactivex.Single
import plugow.aidiagnose.model.Symptom
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface api {

    @GET("symptom")
    fun getSymptoms(@Header("Authorization") token: String?): Single<Response<List<Symptom>>>
}