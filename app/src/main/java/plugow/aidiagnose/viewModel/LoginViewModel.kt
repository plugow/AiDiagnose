package plugow.aidiagnose.viewModel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField

class LoginViewModel : ViewModel() {
    val succesfullVisibility = ObservableField<Boolean>()
    val registerVisibility = ObservableField<Boolean>(true)
    val registerEmail = ObservableField<String>("")
    val firstName = ObservableField<String>("")
    val lastName = ObservableField<String>("")
    val registerPassword = ObservableField<String>("")
    val registerRepeatPassword = ObservableField<String>("")
    val email = ObservableField<String>("")
    val password = ObservableField<String>("")

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
}