package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.rxkotlin.subscribeBy
import plugow.aidiagnose.model.Visit
import plugow.aidiagnose.network.Api
import plugow.aidiagnose.useCase.SharedUseCase
import javax.inject.Inject

class NotificationViewModel @Inject constructor(val api: Api, val sharedUseCase: SharedUseCase) : ViewModel() {
    var visits:MutableLiveData<List<Visit>> = MutableLiveData()
    fun getVisitsList(): LiveData<List<Visit>> {
        if (visits.value==null){
            visits= MutableLiveData()
            loadVisits()
        }
        return visits

    }

    fun loadVisits(){
        val visitsList = api.getVisitsForDoctor(sharedUseCase.token)
                .subscribeBy (
                        onError = {
                            println(it.message)
                        },
                        onSuccess = {
                            visits.value= it.body()
                        }
                )

    }
}