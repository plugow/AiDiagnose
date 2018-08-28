package plugow.aidiagnose.db

import com.raizlabs.android.dbflow.annotation.Database

@Database(version = AiDatabase.VERSION, name = AiDatabase.NAME)
object AiDatabase {
    const val NAME = "AiDatabase"
    const val VERSION = 1
}