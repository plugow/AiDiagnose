package plugow.aidiagnose.view.dialogFragment

import android.app.Dialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_symptoms.*
import kotlinx.android.synthetic.main.map_bottom_sheet.*
import org.jetbrains.anko.toast
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.MapBottomSheetBinding
import plugow.aidiagnose.model.Specialization
import plugow.aidiagnose.recycler.SpecializationAdapter
import plugow.aidiagnose.recycler.SpecializationListener
import plugow.aidiagnose.viewModel.SpecializationListViewModel

class MapBottomSheetFragment : BottomSheetDialogFragment() {
    private val viewModel by lazy{
        ViewModelProviders.of(this).get(SpecializationListViewModel::class.java)
    }
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding= DataBindingUtil.inflate<MapBottomSheetBinding>(inflater,R.layout.map_bottom_sheet,null,false)
        binding.viewModel=viewModel
        val rootView = binding.root

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerBottomSheet.setHasFixedSize(true)
        // use a linear layout manager
        layoutManager = LinearLayoutManager(context)
        recyclerBottomSheet.layoutManager = layoutManager

        mAdapter = SpecializationAdapter(object : SpecializationListener {
            override fun onSpecializationClicked(pos: Int) {
//                    startActivity<PaymentSelectionActivity>("companyServerId" to companyList[pos].serverId)
                context?.toast("działa")
            }
        })
        recyclerBottomSheet.adapter = mAdapter

    }


    companion object {
        fun newInstance(): MapBottomSheetFragment {
            return MapBottomSheetFragment()
        }
    }
}