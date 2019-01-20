package plugow.aidiagnose.view

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FlowManager.init(FlowConfig.Builder(this).build())

        doAsync {
            val prefs: SharedPreferences = getSharedPreferences("com.plugow.aidiagnose", MODE_PRIVATE)
            val isLogged=prefs.getBoolean("isLogged", false)

            uiThread {
                if (isLogged){
                    startActivity<NotificationActivity>()
                }
                else startActivity<LoginActivity>()
                finish()
            }
        }
    }

}
