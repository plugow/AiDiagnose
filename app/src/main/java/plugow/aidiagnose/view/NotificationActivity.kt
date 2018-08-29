package plugow.aidiagnose.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_visit.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivityNotificationBinding
import plugow.aidiagnose.databinding.ActivityVisitBinding
import plugow.aidiagnose.model.Visit
import plugow.aidiagnose.recycler.NotificationAdapter
import plugow.aidiagnose.recycler.NotificationListener
import plugow.aidiagnose.recycler.VisitAdapter
import plugow.aidiagnose.recycler.VisitListener
import plugow.aidiagnose.view.dialogFragment.NotificationDetailSheetFragment
import plugow.aidiagnose.viewModel.NotificationViewModel
import plugow.aidiagnose.viewModel.VisitViewModel
import java.io.Serializable
import javax.inject.Inject

class NotificationActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityNotificationBinding>(this,R.layout.activity_notification)
        val viewModel= ViewModelProviders.of(this, viewModelFactory)[NotificationViewModel::class.java]
        binding.viewModel=viewModel

        recyclerView.setHasFixedSize(true)
        // use a linear layout manager
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                (layoutManager as LinearLayoutManager).orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        viewModel.getVisitsList().observe(this, Observer<List<Visit>> { visitList ->
            mAdapter = NotificationAdapter(visitList!!, object : NotificationListener {
                override fun onVisitClicked(pos: Int) {
                    val detailFragment= NotificationDetailSheetFragment.newInstance()
                    detailFragment.show(supportFragmentManager,"bottom sheet")
                }
            })
            recyclerView.adapter = mAdapter
        })

        toolbarMain.text=getString(R.string.notification_list)
    }
}
