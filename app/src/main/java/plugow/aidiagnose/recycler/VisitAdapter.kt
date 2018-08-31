package plugow.aidiagnose.recycler

import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import plugow.aidiagnose.R
import plugow.aidiagnose.model.Visit
import java.text.SimpleDateFormat
import java.util.*

class VisitAdapter(dataSet:List<Visit>, listener: VisitListener) :  RecyclerView.Adapter<VisitViewHolder>() {
    private val values: List<Visit> = dataSet
    val mListener=listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitViewHolder {
        val inflater = LayoutInflater.from(
                parent.context)
        val v = inflater.inflate(R.layout.visit_item, parent, false)
        return VisitViewHolder(v,mListener)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: VisitViewHolder, position: Int) {
        val visit=values[position]
        if (!visit.isRead){
            holder.doctorName.setTypeface(null, Typeface.BOLD)
        }
        val df = SimpleDateFormat("HH:mm d MMMM yyyy", Locale.getDefault())
        holder.doctorName.text=visit.doctor
        holder.visitDate.text=df.format(visit.date)
    }
}