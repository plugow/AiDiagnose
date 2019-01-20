package plugow.aidiagnose.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import plugow.aidiagnose.R
import plugow.aidiagnose.model.Visit
import java.text.SimpleDateFormat
import java.util.*

class NotificationAdapter(dataSet:List<Visit>, listener: NotificationListener) :  RecyclerView.Adapter<NotificationViewHolder>() {
    private val values: List<Visit> = dataSet
    val mListener=listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val inflater = LayoutInflater.from(
                parent.context)
        val v = inflater.inflate(R.layout.notification_item, parent, false)
        return NotificationViewHolder(v,mListener)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val visit=values[position]
        holder.doctorName.text=visit.patient
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        holder.visitDate.text=df.format(visit.date)
    }
}