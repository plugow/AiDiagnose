package plugow.aidiagnose.view.dialogFragment

import android.app.Dialog
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.NotificationBottomSheetDialogBinding
import plugow.aidiagnose.databinding.SendVisitBottomSheetBinding
import plugow.aidiagnose.viewModel.NotificationDetailViewModel
import plugow.aidiagnose.viewModel.SendVisitViewModel

class NotificationDetailSheetFragment : BottomSheetDialogFragment() {
    private val viewModel by lazy{
        ViewModelProviders.of(this).get(NotificationDetailViewModel::class.java)
    }
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding= DataBindingUtil.inflate<NotificationBottomSheetDialogBinding>(inflater,R.layout.notification_bottom_sheet_dialog,null,false)
        binding.viewModel=viewModel
        val rootView = binding.root

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    companion object {
        fun newInstance(): NotificationDetailSheetFragment {
            return NotificationDetailSheetFragment()
        }
    }
}