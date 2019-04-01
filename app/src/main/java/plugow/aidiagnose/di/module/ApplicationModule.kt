package plugow.aidiagnose.di.module

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import plugow.aidiagnose.db.DbRepository
import plugow.aidiagnose.useCase.SharedUseCase

@Module
abstract class ApplicationModule {
    @Binds
    internal abstract fun bindContext(application: Application): Context


    @Module
    companion object {
        @JvmStatic
        @Provides
        @Reusable
        fun provideSharedPreferences(ctx: Context): SharedUseCase {
            return SharedUseCase(ctx.getSharedPreferences("Authentication", AppCompatActivity.MODE_PRIVATE))
        }

        @JvmStatic
        @Provides
        @Reusable
        fun provideRepo(): DbRepository {
            return DbRepository()
        }
    }
}