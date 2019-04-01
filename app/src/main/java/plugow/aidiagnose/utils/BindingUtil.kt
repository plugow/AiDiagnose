package plugow.aidiagnose.utils

import android.support.design.widget.TextInputLayout
import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import plugow.aidiagnose.recycler.BindableAdapter


@BindingAdapter("errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    if (errorMessage!=null){
        view.error = errorMessage
    }

}

@BindingAdapter("textSizeDp")
fun setTextSizeDp(view: TextView, size: Int?) {
    if (size!=null){
        view.textSize =size.toFloat()
    }

}

@BindingAdapter("img")
fun setImage(view: FloatingActionButton, @DrawableRes image: Int) {
    view.setImageResource(image)
}

@BindingAdapter("bitmap")
fun setBitmap(view: ImageView, bitmap: Bitmap?) {
    if(bitmap!=null){
        view.setImageBitmap(bitmap)
    } else {
        view.setImageResource(0)
    }
}


@BindingAdapter("passwordVisibility")
fun setPasswordVisibility(view: EditText, value: Boolean){
    if (!value) view.transformationMethod = PasswordTransformationMethod()
    else view.transformationMethod = null

}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<T>?) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        items?.let {
            (recyclerView.adapter as BindableAdapter<T>).setData(it)
        }
    }
}


