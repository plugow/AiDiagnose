package plugow.aidiagnose.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivityLoginBinding
import plugow.aidiagnose.viewModel.LoginViewModel





class LoginActivity : AppCompatActivity() {
    private val viewModel by lazy{
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)
        binding.viewModel=viewModel

    }

}
