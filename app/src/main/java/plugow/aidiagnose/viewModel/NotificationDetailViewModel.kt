package plugow.aidiagnose.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import plugow.aidiagnose.model.Visit
import plugow.aidiagnose.network.Api
import javax.inject.Inject

class NotificationDetailViewModel @Inject constructor(val api:Api) : ViewModel() {
    var visits:MutableLiveData<List<Visit>> = MutableLiveData()
}