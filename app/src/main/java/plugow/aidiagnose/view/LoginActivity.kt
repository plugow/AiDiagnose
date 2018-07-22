package plugow.aidiagnose.view

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivityLoginBinding
import plugow.aidiagnose.viewModel.LoginViewModel
import javax.inject.Inject


class LoginActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)
        val viewModel=ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
        binding.viewModel=viewModel

    }

}
