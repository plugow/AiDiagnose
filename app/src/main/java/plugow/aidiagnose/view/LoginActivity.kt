package plugow.aidiagnose.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.register_panel.*
import org.jetbrains.anko.intentFor
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivityLoginBinding
import plugow.aidiagnose.utils.LoginEnum
import plugow.aidiagnose.viewModel.LoginViewModel
import javax.inject.Inject


class LoginActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityLoginBinding>(this,R.layout.activity_login)
        viewModel=ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
        binding.viewModel=viewModel
        val bottomSheetBehavior = BottomSheetBehavior.from(registerBottomSheet)
        viewModel.loginEvent.observe(this, Observer {
            when(it){
                LoginEnum.LOADING -> {
//                    bottomSheetBehavior.isHideable=true
//                    bottomSheetBehavior.state=BottomSheetBehavior.Sta
                }
                LoginEnum.LOGIN -> {startActivity(intentFor<MapsActivity>().setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))}
                LoginEnum.ERROR -> {

                }
            }


        })

    }

}
