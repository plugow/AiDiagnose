package plugow.aidiagnose.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import plugow.aidiagnose.R
import java.lang.ref.WeakReference

class SpecializationViewHolder(var layout: View, val listener:SpecializationListener) : RecyclerView.ViewHolder(layout), View.OnClickListener {
    var specializationName: TextView = layout.findViewById(R.id.specializationName) as TextView
    val listenerRef = WeakReference(listener)

    init { layout.setOnClickListener(this) }
    override fun onClick(v: View?) {
        listenerRef.get()?.onSpecializationClicked(adapterPosition)
    }
}