package plugow.aidiagnose.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import plugow.aidiagnose.network.Api
import plugow.aidiagnose.utils.TimeDeserializer
import java.sql.Time


@Module
object NetworkModule {
    private val BASE_URL = "http://192.168.0.105:1337/"
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideTimeDeserializer(): TimeDeserializer {
        return TimeDeserializer()
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(gson:Gson, timeDeserializer: TimeDeserializer): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGson():Gson{
        return GsonBuilder()
                .setLenient()
                .registerTypeAdapter(Time::class.java, TimeDeserializer())
                .create()
    }

}