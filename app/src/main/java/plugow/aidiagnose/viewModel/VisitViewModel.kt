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

class VisitViewModel @Inject constructor(val api: Api, val sharedUseCase: SharedUseCase) : ViewModel() {
    private lateinit var disposable: Disposable
    var visits:MutableLiveData<List<Visit>> = MutableLiveData()
    init {
        loadVisits()
    }
    fun getVisitsList(): LiveData<List<Visit>> {
        if (visits.value==null){
            visits= MutableLiveData()
            loadVisits()
        }
        return visits

    }

    fun loadVisits(){
        disposable = api.getVisits(sharedUseCase.token)
                .subscribeBy (
                        onError = {},
                        onSuccess = {
                            visits.value= it.body()
                        }
                )

    }

    fun getVisitById(id:Int) = visits.value?.get(id)

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}