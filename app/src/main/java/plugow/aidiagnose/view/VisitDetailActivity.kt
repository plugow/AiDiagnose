package plugow.aidiagnose.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivityVisitDetailBinding
import plugow.aidiagnose.model.Visit
import plugow.aidiagnose.viewModel.VisitDetailViewModel
import javax.inject.Inject

class VisitDetailActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var visit:Visit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityVisitDetailBinding>(this,R.layout.activity_visit_detail)
        val viewModel= ViewModelProviders.of(this, viewModelFactory)[VisitDetailViewModel::class.java]
        binding.viewModel=viewModel
        visit = intent.extras.get("visit") as Visit
        viewModel.setValues(visit)
        viewModel.okEvent.observe(this, Observer {
            onBackPressed()
        })

    }
}
