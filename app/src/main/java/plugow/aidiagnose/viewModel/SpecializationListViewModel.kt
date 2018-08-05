package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.rxkotlin.subscribeBy
import plugow.aidiagnose.model.Specialization
import plugow.aidiagnose.network.ApiService

class SpecializationListViewModel : ViewModel() {
    var specializations: MutableLiveData<List<Specialization>> = MutableLiveData()
    val service: ApiService by lazy { ApiService() }


    fun getSymptomList(): LiveData<List<Specialization>> {
        if (specializations.value==null){
            specializations= MutableLiveData()
            loadSpecializations()
        }
        return specializations

    }

    fun loadSpecializations(){
        val specializationList = service.fetchSpecializations()
                .subscribeBy (
                        onError = {},
                        onSuccess = {specializations.value= it.body()}
                )

    }
}