package plugow.aidiagnose.network

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import plugow.aidiagnose.model.*
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

    fun fetchVisits(): Single<Response<List<Visit>>>{
//        val user : User?= repository.getUserByRole(2)
        val token = "JWT eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImNyZWF0ZWRBdCI6MTUzNTM3NzQ2OTQ2NSwidXBkYXRlZEF0IjoxNTM1Mzc3NDY5NDY1LCJpZCI6MjAsImZpcnN0TmFtZSI6IlVzZXIiLCJsYXN0TmFtZSI6IlVzZXIiLCJlbWFpbCI6InVzZXJAdXNlci5wbCIsImlzQWN0aXZlIjp0cnVlLCJ0ZW1wb3JhcnlQYXNzd29yZCI6IiIsInRlbXBvcmFyeVBhc3N3b3JkRXhwaXJhdGlvblRpbWVzdGFtcCI6bnVsbCwicm9sZSI6MywiZG9jdG9yIjpudWxsfSwiaWF0IjoxNTM1Mzc3NTc3LCJleHAiOjE1Mzc5Njk1NzcsImF1ZCI6InBsdWdvdyIsImlzcyI6InBsdWdvdyJ9.Q54QUvZs0ip7A5PlTDZ7OrspktCbom43nbRNoq-KPO0"
        return api.getVisits(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun fetchVisitsForDoctor(): Single<Response<List<Visit>>>{
//        val user : User?= repository.getUserByRole(2)
        val token = "JWT eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImNyZWF0ZWRBdCI6MTUzNTQ2MTE2NTkyMCwidXBkYXRlZEF0IjoxNTM1NDYxMTcwMzA1LCJpZCI6MywiZmlyc3ROYW1lIjoiRG9jdG9yMiIsImxhc3ROYW1lIjoiRG9jdG9yIiwiZW1haWwiOiJkb2N0b3IyQGRvY3Rvci5wbCIsImlzQWN0aXZlIjp0cnVlLCJ0ZW1wb3JhcnlQYXNzd29yZCI6IiIsInRlbXBvcmFyeVBhc3N3b3JkRXhwaXJhdGlvblRpbWVzdGFtcCI6bnVsbCwicm9sZSI6MiwiZG9jdG9yIjoyfSwiaWF0IjoxNTM1NTUwNjc1LCJleHAiOjE1MzgxNDI2NzUsImF1ZCI6InBsdWdvdyIsImlzcyI6InBsdWdvdyJ9.bSznlWbN5c9M2btdIVcodOFOdIEcknY1-J4Yvbm1FPs"
        return api.getVisitsForDoctor(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun fetchSpecializations(): Single<Response<List<Specialization>>>{
//        val user : User?= repository.getUserByRole(2)
        val token = "JWT eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImNyZWF0ZWRBdCI6MTUzMzQ2MjY5NDI1MSwidXBkYXRlZEF0IjoxNTMzNDYyNjk0MjUxLCJpZCI6MSwiZmlyc3ROYW1lIjoiQWRtaW4iLCJsYXN0TmFtZSI6IkFkbWluIiwicHd6IjoiIiwiZW1haWwiOiJwbHVnb3c3QGdtYWlsLmNvbSIsImlzQWN0aXZlIjp0cnVlLCJ0ZW1wb3JhcnlQYXNzd29yZCI6IiIsInRlbXBvcmFyeVBhc3N3b3JkRXhwaXJhdGlvblRpbWVzdGFtcCI6bnVsbCwicm9sZSI6MX0sImlhdCI6MTUzMzQ2Mjg5NCwiZXhwIjoxNTM2MDU0ODk0LCJhdWQiOiJwbHVnb3ciLCJpc3MiOiJwbHVnb3cifQ.ZhXofDhbXlCu0l8nhwj04BCKG8tpUUkW9Z6YlROj_i0"
        return api.getSpecializations(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun fetchDoctors(): Single<Response<List<Doctor>>>{
//        val user : User?= repository.getUserByRole(2)
        val token = "JWT eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImNyZWF0ZWRBdCI6MTUzMzQ2MjY5NDI1MSwidXBkYXRlZEF0IjoxNTMzNDYyNjk0MjUxLCJpZCI6MSwiZmlyc3ROYW1lIjoiQWRtaW4iLCJsYXN0TmFtZSI6IkFkbWluIiwicHd6IjoiIiwiZW1haWwiOiJwbHVnb3c3QGdtYWlsLmNvbSIsImlzQWN0aXZlIjp0cnVlLCJ0ZW1wb3JhcnlQYXNzd29yZCI6IiIsInRlbXBvcmFyeVBhc3N3b3JkRXhwaXJhdGlvblRpbWVzdGFtcCI6bnVsbCwicm9sZSI6MX0sImlhdCI6MTUzMzQ2Mjg5NCwiZXhwIjoxNTM2MDU0ODk0LCJhdWQiOiJwbHVnb3ciLCJpc3MiOiJwbHVnb3cifQ.ZhXofDhbXlCu0l8nhwj04BCKG8tpUUkW9Z6YlROj_i0"
        return api.getDoctors(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loginUser(body: RequestBody): Single<Response<User>> {
        return Single.defer { api.loginUser(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) }

    }
}