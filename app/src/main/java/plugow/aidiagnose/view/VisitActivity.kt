package plugow.aidiagnose.view

import android.arch.lifecycle.Observer
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
import plugow.aidiagnose.model.Visit
import plugow.aidiagnose.recycler.VisitAdapter
import plugow.aidiagnose.recycler.VisitListener
import plugow.aidiagnose.viewModel.VisitViewModel
import javax.inject.Inject
import plugow.aidiagnose.R.id.recyclerView
import android.support.v7.widget.DividerItemDecoration



class VisitActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityVisitBinding>(this,R.layout.activity_visit)
        val viewModel= ViewModelProviders.of(this, viewModelFactory)[VisitViewModel::class.java]
        binding.viewModel=viewModel

        recyclerView.setHasFixedSize(true)
        // use a linear layout manager
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                (layoutManager as LinearLayoutManager).orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        viewModel.getVisitsList().observe(this, Observer<List<Visit>> { visitList ->
            mAdapter = VisitAdapter(visitList!!, object : VisitListener {
                override fun onVisitClicked(pos: Int) {
//                    viewModel.onVisitClicked(pos)
                }
            })
            recyclerView.adapter = mAdapter
        })
    }
}
