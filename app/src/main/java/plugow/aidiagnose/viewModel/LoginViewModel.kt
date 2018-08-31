package plugow.aidiagnose.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.SharedPreferences
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.support.v4.util.ArrayMap
import android.support.v7.app.AppCompatActivity
import com.raizlabs.android.dbflow.kotlinextensions.save
import io.reactivex.rxkotlin.subscribeBy
import okhttp3.RequestBody
import org.json.JSONObject
import plugow.aidiagnose.R
import plugow.aidiagnose.db.DbRepository
import plugow.aidiagnose.model.User
import plugow.aidiagnose.network.ApiService
import plugow.aidiagnose.utils.LoginEnum
import plugow.aidiagnose.utils.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel @Inject constructor(application:Application) : AndroidViewModel(application) {
    val context=getApplication<Application>()
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
    private val _loginEvent = SingleLiveEvent<LoginEnum>()
    val loginEvent : LiveData<LoginEnum>
        get() = _loginEvent
    val progressVisibility = ObservableBoolean(false)
    val emailEnabled = ObservableBoolean(true)
    val passEnabled = ObservableBoolean(true)
    val loginEnabled = ObservableBoolean(true)
    val requestService by lazy {  ApiService() }

    fun registerButtonListener() {}

    fun loginButtonListener(){
        if (validateSendData()){
            progressVisibility.set(true)
            emailEnabled.set(false)
            passEnabled.set(false)
            loginEnabled.set(false)
            _loginEvent.value = LoginEnum.LOADING
            val jsonParams = ArrayMap<String, String>()
            jsonParams["email"] = email.get()
            jsonParams["password"] = password.get()
            val body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())
            requestService.loginUser(body)
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

    fun validateSendData():Boolean{
        var isGood=true
        if(email.get().equals("") || !android.util.Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()){
            errorUsernameEnabled.set(true)
            emptyUsername.set(context.getString(R.string.invalid_user))
            isGood=false
        }
        if (password.get().equals("")){
            errorPasswordEnabled.set(true)
            emptyPassword.set(context.getString(R.string.invalid_empty_pass))
            isGood=false
        }
        return isGood
    }

    fun onLogin(responseCode: Int?, user: Any?, error: String?)
    {
        progressVisibility.set(false)
        when(responseCode){
            in 200 .. 299 -> {
                val usr=user!! as User
                saveUser(usr)
                _loginEvent.value=LoginEnum.LOGIN
            }
            401 ->{
                emailEnabled.set(true)
                passEnabled.set(true)
                loginEnabled.set(true)
                errorVisibility.set(true)
                val obj = JSONObject(error)
                val code = obj.getString("code")
                if (code.equals("E_WRONG_PASSWORD")) {
                    errorMessage.set(context.getString(R.string.invalid_empty_pass))
                    _loginEvent.value=LoginEnum.ERROR
                } else if (code.equals("E_ACCOUNT_NOT_FOUND")) {
                    errorMessage.set(context.getString(R.string.account_not_found))
                    _loginEvent.value=LoginEnum.ERROR
                } else {
                    errorMessage.set(context.getString(R.string.invalid_data))
                    _loginEvent.value=LoginEnum.ERROR
                }
            }
            406 ->{
                emailEnabled.set(true)
                passEnabled.set(true)
                loginEnabled.set(true)
                errorVisibility.set(true)
                errorMessage.set(context.getString(R.string.account_not_activated))
                _loginEvent.value=LoginEnum.ERROR
            }
            else ->{
                emailEnabled.set(true)
                passEnabled.set(true)
                loginEnabled.set(true)
                errorVisibility.set(true)
                _loginEvent.value=LoginEnum.ERROR
                errorMessage.set(context.getString(R.string.error))
            }
        }
    }

    fun saveUser(user: User){
        user.save()
        val prefs: SharedPreferences = context.getSharedPreferences("com.plugow.aidiagnose", AppCompatActivity.MODE_PRIVATE)
        prefs.edit().putBoolean("isLogged", true).apply()
    }
}