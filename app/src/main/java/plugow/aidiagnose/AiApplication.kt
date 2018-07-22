package plugow.aidiagnose

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import plugow.aidiagnose.di.DaggerAppComponent

class AiApplication : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}