package plugow.aidiagnose.db

import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.rx2.kotlinextensions.rx
import com.raizlabs.android.dbflow.sql.language.SQLite
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.Android
import plugow.aidiagnose.model.User

class DbRepository {


    fun getUser() = (select from User::class).rx().querySingle().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}