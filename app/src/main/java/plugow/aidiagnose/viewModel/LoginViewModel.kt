package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import io.reactivex.rxkotlin.subscribeBy
import org.json.JSONObject
import plugow.aidiagnose.R
import plugow.aidiagnose.model.User
import plugow.aidiagnose.network.Api
import plugow.aidiagnose.useCase.SharedUseCase
import plugow.aidiagnose.utils.LoginEnum
import plugow.aidiagnose.utils.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel @Inject constructor(val ctx:Context, val api: Api, val sharedUseCase: SharedUseCase) : ViewModel() {

    val succesfullVisibility = ObservableField<Boolean>()
    val registerVisibility = ObservableField<Boolean>(true)
    val pwzVisibility = ObservableField<Boolean>(false)
    val errorVisibility = ObservableField<Boolean>(false)
    val registerEmail = ObservableField<String>("")
    val firstName = ObservableField<String>("")
    val lastName = ObservableField<String>("")
    val registerPassword = ObservableField<String>("")
    val registerRepeatPassword = ObservableField<String>("")
    val email = ObservableField<String>("")
    val password = ObservableField<String>("")
    val errorMessage = ObservableField<String>("")
    val switchText = ObservableField<String>(ctx.getString(R.string.patient))

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
    private val _loginEvent = SingleLiveEvent<LoginEnum>()
    val loginEvent : LiveData<LoginEnum>
        get() = _loginEvent
    val progressVisibility = ObservableBoolean(false)
    val emailEnabled = ObservableBoolean(true)
    val passEnabled = ObservableBoolean(true)
    val loginEnabled = ObservableBoolean(true)

    fun registerButtonListener() {}

    fun loginButtonListener(){
        if (validateSendData()){
            progressVisibility.set(true)
            emailEnabled.set(false)
            passEnabled.set(false)
            loginEnabled.set(false)
            _loginEvent.value = LoginEnum.LOADING
            api.loginUser(email.get()!!, password.get()!!)
                    .subscribeBy(
                            onSuccess = {onLogin(it.code(),it.body(), it.errorBody()?.string())},
                            onError = {_loginEvent.value=LoginEnum.ERROR}
                    )

        }
    }

    fun collapseSliderOnClick() {
        registerVisibility.set(true)
        succesfullVisibility.set(false)
    }

    fun onEmailTextChanged() {
        invalidEmailEnabled.set(false)
        invalidEmail.set(null)
    }

    fun onFirstNameTextChanged() {
        invalidNameEnabled.set(false)
        invalidName.set(null)
    }

    fun onLastNameTextChanged() {
        invalidLastNameEnabled.set(false)
        invalidLastName.set(null)
    }

    fun onFirstPassTextChanged() {
        invalidFirstPassEnabled.set(false)
        invalidFirstPass.set(null)
    }

    fun onSecondPassTextChanged() {
        invalidSecondPassEnabled.set(false)
        invalidSecondPass.set(null)
    }

    fun onUsernameTextChanged(){
        errorUsernameEnabled.set(false)
        emptyUsername.set(null)
    }

    fun onPasswordTextChanged(){
        errorPasswordEnabled.set(false)
        emptyPassword.set(null)
    }

    fun onPwzChanged(){
        invalidPwzEnabled.set(false)
        pwz.set(null)
    }

    fun onSwitchChanged(checked: Boolean){
        if (checked){
            switchText.set(ctx.getString(R.string.doctor))
            pwzVisibility.set(true)
        }
        else{
            switchText.set(ctx.getString(R.string.patient))
            pwzVisibility.set(false)
        }

    }

    fun validateSendData():Boolean{
        var isGood=true
        if(email.get().equals("") || !android.util.Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()){
            errorUsernameEnabled.set(true)
            emptyUsername.set(ctx.getString(R.string.invalid_user))
            isGood=false
        }
        if (password.get().equals("")){
            errorPasswordEnabled.set(true)
            emptyPassword.set(ctx.getString(R.string.invalid_empty_pass))
            isGood=false
        }
        return isGood
    }

    private fun onLogin(responseCode: Int?, user: Any?, error: String?)
    {
        progressVisibility.set(false)
        when(responseCode){
            in 200 .. 299 -> {
                val usr=user!! as User
                usr.save()
                sharedUseCase.isLogged=true
                sharedUseCase.token = usr.token
                _loginEvent.value=LoginEnum.LOGIN
            }
            401 ->{
                emailEnabled.set(true)
                passEnabled.set(true)
                loginEnabled.set(true)
                errorVisibility.set(true)
                val obj = JSONObject(error)
                val code = obj.getString("code")
                when (code) {
                    "E_WRONG_PASSWORD" -> {
                        errorMessage.set(ctx.getString(R.string.invalid_empty_pass))
                        _loginEvent.value=LoginEnum.ERROR
                    }
                    "E_ACCOUNT_NOT_FOUND" -> {
                        errorMessage.set(ctx.getString(R.string.account_not_found))
                        _loginEvent.value=LoginEnum.ERROR
                    }
                    else -> {
                        errorMessage.set(ctx.getString(R.string.invalid_data))
                        _loginEvent.value=LoginEnum.ERROR
                    }
                }
            }
            406 ->{
                emailEnabled.set(true)
                passEnabled.set(true)
                loginEnabled.set(true)
                errorVisibility.set(true)
                errorMessage.set(ctx.getString(R.string.account_not_activated))
                _loginEvent.value=LoginEnum.ERROR
            }
            else ->{
                emailEnabled.set(true)
                passEnabled.set(true)
                loginEnabled.set(true)
                errorVisibility.set(true)
                _loginEvent.value=LoginEnum.ERROR
                errorMessage.set(ctx.getString(R.string.error))
            }
        }
    }

}