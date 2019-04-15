package plugow.aidiagnose.recycler

import android.graphics.Typeface
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import plugow.aidiagnose.R
import plugow.aidiagnose.model.Visit
import java.text.SimpleDateFormat
import java.util.*

class VisitAdapter(listener: VisitListener) :  RecyclerView.Adapter<VisitViewHolder>(), BindableAdapter<Visit> {
    private var values: ArrayList<Visit> = arrayListOf()
    override fun setData(items: List<Visit>) {
        values = items as ArrayList<Visit>
        notifyDataSetChanged()
    }
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