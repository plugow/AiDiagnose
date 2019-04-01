package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import plugow.aidiagnose.model.Specialization
import plugow.aidiagnose.network.Api
import plugow.aidiagnose.useCase.SharedUseCase
import javax.inject.Inject

class SpecializationListViewModel @Inject constructor(val api: Api, val sharedUseCase: SharedUseCase) : ViewModel() {
    private lateinit var disposable: Disposable
    var specializations: MutableLiveData<List<Specialization>> = MutableLiveData()


    fun getSymptomList(): LiveData<List<Specialization>> {
        if (specializations.value==null){
            specializations= MutableLiveData()
            loadSpecializations()
        }
        return specializations

    }
    init {
        loadSpecializations()
    }

    fun loadSpecializations(){
        disposable = api.getSpecializations(sharedUseCase.token)
                .subscribeBy (
                        onError = {},
                        onSuccess = {specializations.value= it.body()}
                )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}