package plugow.aidiagnose.recycler

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import plugow.aidiagnose.R
import plugow.aidiagnose.model.Specialization
import plugow.aidiagnose.model.Symptom

class SpecializationAdapter(listener: SpecializationListener) :  RecyclerView.Adapter<SpecializationViewHolder>(), BindableAdapter<Specialization> {
    private var values: ArrayList<Specialization> = arrayListOf()
    override fun setData(items: List<Specialization>) {
        values = items as ArrayList<Specialization>
        notifyDataSetChanged()
    }
    val mListener=listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecializationViewHolder {
        val inflater = LayoutInflater.from(
                parent.context)
        val v = inflater.inflate(R.layout.specialization_item, parent, false)
        return SpecializationViewHolder(v,mListener)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: SpecializationViewHolder, position: Int) {
        val specialization=values[position]
        holder.specializationName.text=specialization.name
        if (position%2!=0) holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.layout.context,R.color.white))
        else holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.layout.context, R.color.lightGray))
    }
}