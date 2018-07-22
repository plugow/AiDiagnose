package plugow.aidiagnose.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import plugow.aidiagnose.AiApplication
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent : AndroidInjector<AiApplication>{

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}