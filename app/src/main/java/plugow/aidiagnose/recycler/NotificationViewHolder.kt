package plugow.aidiagnose.recycler

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import plugow.aidiagnose.R
import java.lang.ref.WeakReference

class NotificationViewHolder(var layout: View, val listener:NotificationListener) : RecyclerView.ViewHolder(layout), View.OnClickListener {
    var doctorName: TextView = layout.findViewById(R.id.nameTextView) as TextView
    var visitDate: TextView = layout.findViewById(R.id.dateTextView) as TextView
    val listenerRef = WeakReference(listener)

    init { layout.setOnClickListener(this) }
    override fun onClick(v: View?) {
        listenerRef.get()?.onVisitClicked(adapterPosition)
    }
}