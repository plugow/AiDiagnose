package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import plugow.aidiagnose.model.Visit
import plugow.aidiagnose.utils.SingleLiveEvent
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class VisitDetailViewModel @Inject constructor() : ViewModel() {
    var doctorTextView:ObservableField<String> = ObservableField("")
    var placeTextView:ObservableField<String> = ObservableField("")
    var dateTextView:ObservableField<String> = ObservableField("")
    var commentTextView:ObservableField<String> = ObservableField("")
    private val _okEvent = SingleLiveEvent<Any>()
    val okEvent : LiveData<Any>
        get() = _okEvent


    fun setValues(visit:Visit){
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        doctorTextView.set(visit.doctor)
        placeTextView.set(visit.place)
        dateTextView.set(df.format(visit.date))
        commentTextView.set(visit.comment)
    }

    fun okClicked(){
        _okEvent.call()


    }
}