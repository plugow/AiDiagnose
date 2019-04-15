package plugow.aidiagnose.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
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
        val df = SimpleDateFormat("d MMMM yyyy HH:mm", Locale.getDefault())
        doctorTextView.set(visit.doctor)
        placeTextView.set(visit.place)
        dateTextView.set(df.format(visit.date))
        commentTextView.set(visit.comment)
    }

    fun okClicked(){
        _okEvent.call()


    }
}