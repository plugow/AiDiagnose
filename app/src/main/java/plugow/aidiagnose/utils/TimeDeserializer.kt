package plugow.aidiagnose.utils

import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonDeserializer
import java.lang.reflect.Type
import java.sql.Time
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class TimeDeserializer : JsonDeserializer<Time> {

    private val TIME_FORMAT = "HH:mm:ss"

    @Throws(JsonParseException::class)
    override fun deserialize(jsonElement: JsonElement, typeOF: Type,
                             context: JsonDeserializationContext): Time {
        try {

            val s = jsonElement.asString
            val sdf = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
            sdf.parse(s)
            val ms = sdf.parse(s).time
            return Time(ms)
        } catch (e: ParseException) {
        }

        throw JsonParseException("Unparseable time: \"" + jsonElement.asString
                + "\". Supported formats: " + TIME_FORMAT)
    }
}