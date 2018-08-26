package plugow.aidiagnose.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import plugow.aidiagnose.R
import javax.inject.Inject

class LoginViewModel @Inject constructor(application:Application) : AndroidViewModel(application) {
    val context=getApplication<Application>()
    val succesfullVisibility = ObservableField<Boolean>()
    val registerVisibility = ObservableField<Boolean>(true)
    val pwzVisibility = ObservableField<Boolean>(false)
    val registerEmail = ObservableField<String>("")
    val firstName = ObservableField<String>("")
    val lastName = ObservableField<String>("")
    val registerPassword = ObservableField<String>("")
    val registerRepeatPassword = ObservableField<String>("")
    val email = ObservableField<String>("")
    val password = ObservableField<String>("")
    val switchText = ObservableField<String>(context.getString(R.string.patient))

    val invalidEmailEnabled = ObservableBoolean(false)
    val invalidEmail = ObservableField<String>("")
    val invalidNameEnabled = ObservableBoolean(false)
    val invalidName = ObservableField<String>("")
    val invalidLastNameEnabled = ObservableBoolean(false)
    val invalidLastName = ObservableField<String>("")
    val invalidFirstPassEnabled = ObservableBoolean(false)
    val invalidFirstPass = ObservableField<String>("")
    val invalidSecondPassEnabled = ObservableBoolean(false)
    val invalidSecondPass = ObservableField<String>("")
    val emptyUsername=ObservableField<String>()
    val emptyPassword=ObservableField<String>()
    val errorUsernameEnabled=ObservableBoolean(false)
    val errorPasswordEnabled=ObservableBoolean(false)
    val invalidPwzEnabled = ObservableBoolean(false)
    val invalidPwz = ObservableField<String>("")
    val pwz = ObservableField<String>("")

    fun registerButtonListener() {}

    fun loginButtonListener(){}


    fun collapseSliderOnClick() {
        registerVisibility.set(true)
        succesfullVisibility.set(false)
    }

    fun onEmailTextChanged(text: CharSequence) {
        invalidEmailEnabled.set(false)
        invalidEmail.set(null)
    }

    fun onFirstNameTextChanged(text: CharSequence) {
        invalidNameEnabled.set(false)
        invalidName.set(null)
    }

    fun onLastNameTextChanged(text: CharSequence) {
        invalidLastNameEnabled.set(false)
        invalidLastName.set(null)
    }

    fun onFirstPassTextChanged(text: CharSequence) {
        invalidFirstPassEnabled.set(false)
        invalidFirstPass.set(null)
    }

    fun onSecondPassTextChanged(text: CharSequence) {
        invalidSecondPassEnabled.set(false)
        invalidSecondPass.set(null)
    }

    fun onUsernameTextChanged(text:CharSequence){
        errorUsernameEnabled.set(false)
        emptyUsername.set(null)
    }

    fun onPasswordTextChanged(text: CharSequence){
        errorPasswordEnabled.set(false)
        emptyPassword.set(null)
    }

    fun onPwzChanged(text: CharSequence){
        invalidPwzEnabled.set(false)
        pwz.set(null)
    }

    fun onSwitchChanged(checked: Boolean){
        if (checked){
            switchText.set(context.getString(R.string.doctor))
            pwzVisibility.set(true)
        }
        else{
            switchText.set(context.getString(R.string.patient))
            pwzVisibility.set(false)
        }

    }
}