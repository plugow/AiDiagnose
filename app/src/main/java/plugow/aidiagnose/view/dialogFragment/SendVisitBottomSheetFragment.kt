package plugow.aidiagnose.view.dialogFragment

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_symptoms.*
import kotlinx.android.synthetic.main.map_bottom_sheet.*
import org.jetbrains.anko.toast
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.MapBottomSheetBinding
import plugow.aidiagnose.databinding.SendVisitBottomSheetBinding
import plugow.aidiagnose.model.Specialization
import plugow.aidiagnose.recycler.SpecializationAdapter
import plugow.aidiagnose.recycler.SpecializationListener
import plugow.aidiagnose.viewModel.SendVisitViewModel
import plugow.aidiagnose.viewModel.SpecializationListViewModel

class SendVisitBottomSheetFragment : BottomSheetDialogFragment() {
    private val viewModel by lazy{
        ViewModelProviders.of(this).get(SendVisitViewModel::class.java)
    }
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding= DataBindingUtil.inflate<SendVisitBottomSheetBinding>(inflater,R.layout.send_visit_bottom_sheet,null,false)
        binding.viewModel=viewModel
        val rootView = binding.root

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    companion object {
        fun newInstance(): SendVisitBottomSheetFragment {
            return SendVisitBottomSheetFragment()
        }
    }
}