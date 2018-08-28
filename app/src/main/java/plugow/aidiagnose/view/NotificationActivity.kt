package plugow.aidiagnose.view

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import dagger.android.support.DaggerAppCompatActivity
import plugow.aidiagnose.R
import javax.inject.Inject

class NotificationActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
    }
}
