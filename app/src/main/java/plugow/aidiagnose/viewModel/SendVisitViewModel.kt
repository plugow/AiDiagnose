package plugow.aidiagnose.viewModel


import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField

class SendVisitViewModel : ViewModel() {
    var doctor:ObservableField<String> = ObservableField("dr Jan Kowalski")
    var specializations:ObservableField<String> = ObservableField("Internista")
    var address:ObservableField<String> = ObservableField("ul. Mikołaja Kopernika 21")


}