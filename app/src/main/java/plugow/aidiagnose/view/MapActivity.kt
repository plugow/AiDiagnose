package plugow.aidiagnose.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivityMapBinding
import plugow.aidiagnose.viewModel.MapViewModel
import javax.inject.Inject

class MapActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityMapBinding>(this,R.layout.activity_symptoms)
        val viewModel= ViewModelProviders.of(this, viewModelFactory)[MapViewModel::class.java]
        binding.viewModel=viewModel
    }
}
