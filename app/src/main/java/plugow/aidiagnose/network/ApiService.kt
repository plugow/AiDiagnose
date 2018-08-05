package plugow.aidiagnose.network

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import plugow.aidiagnose.model.Symptom
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    val api by lazy { Retrofit.Builder()
            .baseUrl("http://192.168.0.104:1337/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api::class.java) }

    fun fetchSymptoms(): Single<Response<List<Symptom>>>{
//        val user : User?= repository.getUserByRole(2)
        val token = "JWT eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImNyZWF0ZWRBdCI6MTUzMzQ2MjY5NDI1MSwidXBkYXRlZEF0IjoxNTMzNDYyNjk0MjUxLCJpZCI6MSwiZmlyc3ROYW1lIjoiQWRtaW4iLCJsYXN0TmFtZSI6IkFkbWluIiwicHd6IjoiIiwiZW1haWwiOiJwbHVnb3c3QGdtYWlsLmNvbSIsImlzQWN0aXZlIjp0cnVlLCJ0ZW1wb3JhcnlQYXNzd29yZCI6IiIsInRlbXBvcmFyeVBhc3N3b3JkRXhwaXJhdGlvblRpbWVzdGFtcCI6bnVsbCwicm9sZSI6MX0sImlhdCI6MTUzMzQ2Mjg5NCwiZXhwIjoxNTM2MDU0ODk0LCJhdWQiOiJwbHVnb3ciLCJpc3MiOiJwbHVnb3cifQ.ZhXofDhbXlCu0l8nhwj04BCKG8tpUUkW9Z6YlROj_i0"
        return api.getSymptoms(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}