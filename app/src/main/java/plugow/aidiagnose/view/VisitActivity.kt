package plugow.aidiagnose.view

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_visit.*
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivityVisitBinding
import plugow.aidiagnose.recycler.VisitAdapter
import plugow.aidiagnose.recycler.VisitListener
import plugow.aidiagnose.viewModel.VisitViewModel
import javax.inject.Inject
import androidx.recyclerview.widget.DividerItemDecoration
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
