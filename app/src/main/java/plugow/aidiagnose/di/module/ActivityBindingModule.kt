package plugow.aidiagnose.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import plugow.aidiagnose.di.scope.ActivityScoped
import plugow.aidiagnose.view.LoginActivity

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun loginActivity(): LoginActivity
}