package plugow.aidiagnose.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import plugow.aidiagnose.AiApplication
import plugow.aidiagnose.di.module.ActivityBindingModule
import plugow.aidiagnose.di.module.ApplicationModule
import plugow.aidiagnose.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ActivityBindingModule::class,
    ViewModelModule::class,
    ApplicationModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent : AndroidInjector<AiApplication>{

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}