package plugow.aidiagnose.db

import com.raizlabs.android.dbflow.sql.language.SQLite
import plugow.aidiagnose.model.User

class DbRepository {

    val user: User?
        get() = SQLite.select()
                .from(User::class.java)
                .querySingle()
}