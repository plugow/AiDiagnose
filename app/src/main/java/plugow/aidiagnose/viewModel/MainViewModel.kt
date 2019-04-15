package plugow.aidiagnose.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxkotlin.subscribeBy
import plugow.aidiagnose.db.DbRepository
import plugow.aidiagnose.model.Doctor
import plugow.aidiagnose.network.Api
import plugow.aidiagnose.useCase.SharedUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(val api: Api, val repo:DbRepository ) : ViewModel() {
    fun getUser() = repo.getUser()

}