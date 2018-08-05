package plugow.aidiagnose.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import plugow.aidiagnose.R
import java.lang.ref.WeakReference

class SymptomViewHolder(var layout: View, val listener:SymptomListener) : RecyclerView.ViewHolder(layout), View.OnClickListener {
    var symptomName: TextView = layout.findViewById(R.id.symptomName) as TextView
    val listenerRef = WeakReference(listener)

    init { layout.setOnClickListener(this) }
    override fun onClick(v: View?) {
        listenerRef.get()?.onSymptomClicked(adapterPosition)
    }
}