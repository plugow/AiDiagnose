package plugow.aidiagnose.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_visit.*
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivityVisitBinding
import plugow.aidiagnose.recycler.VisitAdapter
import plugow.aidiagnose.recycler.VisitListener
import plugow.aidiagnose.viewModel.VisitViewModel
import javax.inject.Inject
import android.support.v7.widget.DividerItemDecoration
import org.jetbrains.anko.startActivity
import java.io.Serializable


class VisitActivity : DaggerAppCompatActivity() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel:VisitViewModel
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityVisitBinding>(this,R.layout.activity_visit)
        viewModel= ViewModelProviders.of(this, viewModelFactory)[VisitViewModel::class.java]
        binding.viewModel=viewModel

        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                (layoutManager as LinearLayoutManager).orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        mAdapter = VisitAdapter(object : VisitListener {
            override fun onVisitClicked(pos: Int) {
                startActivity<VisitDetailActivity>("visit" to viewModel.getVisitById(pos) as Serializable)
            }
        })
        recyclerView.adapter = mAdapter

    }
}
