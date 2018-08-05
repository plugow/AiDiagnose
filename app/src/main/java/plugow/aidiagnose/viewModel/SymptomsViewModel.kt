package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import plugow.aidiagnose.model.Symptom
import javax.inject.Inject

class SymptomsViewModel @Inject constructor() : ViewModel() {
    var companies:MutableLiveData<List<Symptom>> = MutableLiveData()


    fun getSymptomList(): LiveData<List<Symptom>> {
        if (companies.value==null){
            companies= MutableLiveData()
            loadCompanies()
        }
        return companies

    }

    fun loadCompanies(){
//        val repository = DataRepository()
//        val companiesList: List<Symptom>? = repository.getCompanies()
//        val companiesList: List<Symptom>
        companies.value= listOf(Symptom(1,"dupa"), Symptom(2,"drugi symptom"), Symptom(3, "trzeci symptom"))
    }

}