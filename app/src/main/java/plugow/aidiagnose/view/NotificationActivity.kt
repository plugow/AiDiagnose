package plugow.aidiagnose.view

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_visit.*
import kotlinx.android.synthetic.main.toolbar.*
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivityNotificationBinding
import plugow.aidiagnose.recycler.NotificationAdapter
import plugow.aidiagnose.recycler.NotificationListener
import plugow.aidiagnose.view.dialogFragment.NotificationDetailSheetFragment
import plugow.aidiagnose.viewModel.NotificationViewModel
import javax.inject.Inject

class NotificationActivity : DaggerAppCompatActivity() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel:NotificationViewModel
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityNotificationBinding>(this,R.layout.activity_notification)
        viewModel= ViewModelProviders.of(this, viewModelFactory)[NotificationViewModel::class.java]
        binding.viewModel=viewModel

        recyclerView.setHasFixedSize(true)
        // use a linear layout manager
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                (layoutManager as LinearLayoutManager).orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        mAdapter = NotificationAdapter( object : NotificationListener {
            override fun onVisitClicked(pos: Int) {
                val detailFragment= NotificationDetailSheetFragment.newInstance()
                detailFragment.show(supportFragmentManager,"bottom sheet")
            }
        })
        recyclerView.adapter = mAdapter

        toolbarMain.text=getString(R.string.notification_list)
    }
}
