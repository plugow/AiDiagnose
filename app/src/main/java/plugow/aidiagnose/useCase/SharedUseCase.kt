package plugow.aidiagnose.useCase

import android.content.SharedPreferences
import plugow.aidiagnose.utils.bool
import plugow.aidiagnose.utils.string


class SharedUseCase(val prefs: SharedPreferences) {
    var name by prefs.string()
    var token by prefs.string()
    var isLogged by prefs.bool()
}