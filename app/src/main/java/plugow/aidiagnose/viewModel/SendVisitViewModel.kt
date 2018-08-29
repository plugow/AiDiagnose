package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import io.reactivex.rxkotlin.subscribeBy
import plugow.aidiagnose.model.Specialization
import plugow.aidiagnose.network.ApiService

class SendVisitViewModel : ViewModel() {
    val service: ApiService by lazy { ApiService() }
    var doctor:ObservableField<String> = ObservableField("dr Jan Kowalski")
    var specializations:ObservableField<String> = ObservableField("Internista")
    var address:ObservableField<String> = ObservableField("ul. Mikołaja Kopernika 21")


}