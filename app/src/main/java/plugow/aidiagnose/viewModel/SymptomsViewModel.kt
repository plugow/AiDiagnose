package plugow.aidiagnose.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import io.reactivex.rxkotlin.subscribeBy
import plugow.aidiagnose.model.Symptom
import plugow.aidiagnose.network.ApiService
import javax.inject.Inject

class SymptomsViewModel @Inject constructor() : ViewModel() {
    var symptoms:MutableLiveData<List<Symptom>> = MutableLiveData()
    val service:ApiService by lazy {ApiService()}
    var symptomsAmount=1
    var secondVisibility:ObservableBoolean= ObservableBoolean(false)
    var thirdVisibility:ObservableBoolean= ObservableBoolean(false)
    var fourthVisibility:ObservableBoolean= ObservableBoolean(false)
    var fifthVisibility:ObservableBoolean= ObservableBoolean(false)
    var focusedEditText:Int=1
    var firstText:ObservableField<String> = ObservableField("")
    var secondText:ObservableField<String> = ObservableField("")
    var thirdText:ObservableField<String> = ObservableField("")
    var fourthText:ObservableField<String> = ObservableField("")
    var fifthText:ObservableField<String> = ObservableField("")


    fun getSymptomList(): LiveData<List<Symptom>> {
        if (symptoms.value==null){
            symptoms= MutableLiveData()
            loadSymptoms()
        }
        return symptoms

    }

    fun loadSymptoms(){
        val symptomsList = service.fetchSymptoms()
                .subscribeBy (
                        onError = {},
                        onSuccess = {symptoms.value= it.body()}
                )

    }

    fun onFirstSymptom(text: CharSequence){

    }

    fun onSecondSymptom(text: CharSequence){

    }

    fun secondClick(){
        secondVisibility.set(false)
        symptomsAmount-=1
        secondText.set("")
    }

    fun onThirdSymptom(text: CharSequence){

    }

    fun thirdClick(){
        thirdVisibility.set(false)
        symptomsAmount-=1
        thirdText.set("")
    }

    fun onFourthSymptom(text: CharSequence){

    }

    fun fourthClick(){
        fourthVisibility.set(false)
        symptomsAmount-=1
        fourthText.set("")
    }

    fun onFifthSymptom(text: CharSequence){

    }

    fun fifthClick(){
        fifthVisibility.set(false)
        symptomsAmount-=1
        fifthText.set("")
    }

    fun plusClicked(){
        symptomsAmount+=1
        when(symptomsAmount){
            in 2 .. 5 -> setFirstFreeVisible()
            6 -> symptomsAmount=5
        }

    }

    fun onSymptomClicked(pos:Int){
        when(focusedEditText){
            1->firstText.set(symptoms.value!![pos].name)
            2->secondText.set(symptoms.value!![pos].name)
            3->thirdText.set(symptoms.value!![pos].name)
            4->fourthText.set(symptoms.value!![pos].name)
            5->fifthText.set(symptoms.value!![pos].name)
        }


    }

    fun setFirstFreeVisible(){
        if (!secondVisibility.get()) secondVisibility.set(true)
        else{
            if (!thirdVisibility.get()) thirdVisibility.set(true)
            else{
                if (!fourthVisibility.get()) fourthVisibility.set(true)
                else{
                    fifthVisibility.set(true)
                }
            }
        }
    }

    fun onFocus(pos:Int){
        focusedEditText=pos
    }
}