package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.rxkotlin.subscribeBy
import plugow.aidiagnose.model.Doctor
import plugow.aidiagnose.network.ApiService
import javax.inject.Inject

class MapViewModel @Inject constructor() : ViewModel() {
    var doctors: MutableLiveData<List<Doctor>> = MutableLiveData()
    val service: ApiService by lazy { ApiService() }


    fun getDoctorList(): LiveData<List<Doctor>> {
        if (doctors.value==null){
            doctors= MutableLiveData()
            loadDoctors()
        }
        return doctors

    }

    fun loadDoctors(){
        val doctorsList = service.fetchDoctors()
                .subscribeBy (
                        onError = {},
                        onSuccess = {doctors.value= it.body()}
                )

    }
}