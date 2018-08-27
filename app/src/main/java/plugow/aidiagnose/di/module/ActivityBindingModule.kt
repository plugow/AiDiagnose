package plugow.aidiagnose.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import plugow.aidiagnose.di.scope.ActivityScoped
import plugow.aidiagnose.view.LoginActivity
import plugow.aidiagnose.view.MapsActivity
import plugow.aidiagnose.view.SymptomsActivity
import plugow.aidiagnose.view.VisitActivity

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun loginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun symptomsActivity(): SymptomsActivity

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun mapActivity(): MapsActivity

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun visitActivity(): VisitActivity
}