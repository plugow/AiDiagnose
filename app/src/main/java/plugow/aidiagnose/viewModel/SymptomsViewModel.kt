package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.rxkotlin.subscribeBy
import plugow.aidiagnose.model.Symptom
import plugow.aidiagnose.network.ApiService
import javax.inject.Inject

class SymptomsViewModel @Inject constructor() : ViewModel() {
    var companies:MutableLiveData<List<Symptom>> = MutableLiveData()
    val service:ApiService by lazy {ApiService()}


    fun getSymptomList(): LiveData<List<Symptom>> {
        if (companies.value==null){
            companies= MutableLiveData()
            loadCompanies()
        }
        return companies

    }

    fun loadCompanies(){
        val symptomsList = service.fetchSymptoms()
                .subscribeBy (
                        onError = {},
                        onSuccess = {companies.value= it.body()}
                )

    }

}