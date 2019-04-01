package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import plugow.aidiagnose.model.Doctor
import plugow.aidiagnose.network.Api
import plugow.aidiagnose.useCase.SharedUseCase
import javax.inject.Inject

class MapViewModel @Inject constructor(val api: Api, val sharedUseCase: SharedUseCase) : ViewModel() {
    var doctors: MutableLiveData<List<Doctor>> = MutableLiveData()
    private lateinit var disposable: Disposable


    fun getDoctorList(): LiveData<List<Doctor>> {
        if (doctors.value==null){
            doctors= MutableLiveData()
            loadDoctors()
        }
        return doctors

    }

    fun loadDoctors(){
        disposable = api.getDoctors(sharedUseCase.token)
                .subscribeBy (
                        onError = {},
                        onSuccess = {doctors.value= it.body()}
                )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}