package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.rxkotlin.subscribeBy
import plugow.aidiagnose.model.Visit
import plugow.aidiagnose.network.ApiService
import javax.inject.Inject

class VisitViewModel @Inject constructor() : ViewModel() {
    var visits:MutableLiveData<List<Visit>> = MutableLiveData()
    val service: ApiService by lazy { ApiService() }
    fun getVisitsList(): LiveData<List<Visit>> {
        if (visits.value==null){
            visits= MutableLiveData()
            loadVisits()
        }
        return visits

    }

    fun loadVisits(){
        val visitsList = service.fetchVisits()
                .subscribeBy (
                        onError = {},
                        onSuccess = {
                            visits.value= it.body()
                        }
                )

    }
}