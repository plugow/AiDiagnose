package plugow.aidiagnose.recycler

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import plugow.aidiagnose.R
import plugow.aidiagnose.model.Symptom

class SymptomAdapter(private val dataSet:List<Symptom>, private val listener: SymptomListener) :  RecyclerView.Adapter<SymptomViewHolder>() {
    private val values: List<Symptom> = dataSet
    val mListener=listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomViewHolder {
        val inflater = LayoutInflater.from(
                parent.context)
        val v = inflater.inflate(R.layout.symptom_item, parent, false)
        return SymptomViewHolder(v,mListener)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: SymptomViewHolder, position: Int) {
        val nameCompany=values[position]
        holder.symptomName.text=nameCompany.name
        if (position%2!=0) holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.layout.context,R.color.white))
        else holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.layout.context, R.color.lightGray))
    }
}