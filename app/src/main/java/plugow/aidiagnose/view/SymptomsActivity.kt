package plugow.aidiagnose.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivitySymptomsBinding
import plugow.aidiagnose.viewModel.SymptomsViewModel
import javax.inject.Inject

class SymptomsActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivitySymptomsBinding>(this,R.layout.activity_symptoms)
        val viewModel= ViewModelProviders.of(this, viewModelFactory)[SymptomsViewModel::class.java]
        binding.viewModel=viewModel
    }
}
