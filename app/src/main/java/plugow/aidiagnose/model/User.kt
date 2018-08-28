package plugow.aidiagnose.model

import com.google.gson.annotations.SerializedName
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import plugow.aidiagnose.db.AiDatabase

@Table(database = AiDatabase::class)
data class User(
        @PrimaryKey(autoincrement = true)
        @SerializedName("id")
        @Column(name = "id")
        var id: Long = -1,

        @Column(name = "first_name")
        var firstName: String = "",

        @Column(name = "last_name")
        var lastName: String = "",

        @Column
        var token: String? = ""
) : BaseModel()