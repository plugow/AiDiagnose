package plugow.aidiagnose.utils

import android.support.design.widget.TextInputLayout
import android.databinding.BindingAdapter



@BindingAdapter("errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    if (errorMessage!=null){
        view.error = errorMessage
    }

}