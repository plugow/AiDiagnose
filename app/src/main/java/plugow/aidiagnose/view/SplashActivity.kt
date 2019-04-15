package plugow.aidiagnose.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.startActivity
import plugow.aidiagnose.useCase.SharedUseCase
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {
    @Inject lateinit var sharedUseCase: SharedUseCase
    private lateinit var disposable:Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FlowManager.init(FlowConfig.Builder(this).build())

        disposable=Single.fromCallable {
            sharedUseCase.isLogged
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            if (it) startActivity<MainActivity>()
                            else startActivity<LoginActivity>()
                        },
                        onError = {startActivity<LoginActivity>()}
                )
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}
