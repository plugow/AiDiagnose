package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.rxkotlin.subscribeBy
import plugow.aidiagnose.db.DbRepository
import plugow.aidiagnose.model.Doctor
import plugow.aidiagnose.network.Api
import plugow.aidiagnose.useCase.SharedUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(val api: Api, val repo:DbRepository ) : ViewModel() {
    fun getUser() = repo.getUser()

}