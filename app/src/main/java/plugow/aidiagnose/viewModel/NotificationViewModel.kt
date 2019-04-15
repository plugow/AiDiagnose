package plugow.aidiagnose.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import plugow.aidiagnose.model.Visit
import plugow.aidiagnose.network.Api
import plugow.aidiagnose.useCase.SharedUseCase
import javax.inject.Inject

class NotificationViewModel @Inject constructor(val api: Api, val sharedUseCase: SharedUseCase) : ViewModel() {
    private lateinit var disposable: Disposable
    var visits:MutableLiveData<List<Visit>> = MutableLiveData()
    fun getVisitsList(): LiveData<List<Visit>> {
        if (visits.value==null){
            visits= MutableLiveData()
            loadVisits()
        }
        return visits

    }

    init {
        loadVisits()
    }
    fun loadVisits(){
        disposable = api.getVisitsForDoctor(sharedUseCase.token)
                .subscribeBy (
                        onError = {
                            println(it.message)
                        },
                        onSuccess = {
                            visits.value= it.body()
                        }
                )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}