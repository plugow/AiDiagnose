package plugow.aidiagnose.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_symptoms.*
import kotlinx.android.synthetic.main.search_bar.*
import org.jetbrains.anko.toast
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivitySymptomsBinding
import plugow.aidiagnose.model.Symptom
import plugow.aidiagnose.recycler.SymptomAdapter
import plugow.aidiagnose.recycler.SymptomListener
import plugow.aidiagnose.viewModel.SymptomsViewModel
import javax.inject.Inject

class SymptomsActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivitySymptomsBinding>(this,R.layout.activity_symptoms)
        val viewModel= ViewModelProviders.of(this, viewModelFactory)[SymptomsViewModel::class.java]
        binding.viewModel=viewModel
        recyclerView.setHasFixedSize(true)
        // use a linear layout manager
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        viewModel.getSymptomList().observe(this, Observer<List<Symptom>> { companyList ->
            mAdapter = SymptomAdapter(companyList!!, object : SymptomListener {
                override fun onSymptomClicked(pos: Int) {
                    viewModel.onSymptomClicked(pos)
                }
            })
            recyclerView.adapter = mAdapter
        })

        editText.onFocusChangeListener= View.OnFocusChangeListener { v, hasFocus -> if (hasFocus)viewModel.onFocus(1) }
        editText2.onFocusChangeListener= View.OnFocusChangeListener { v, hasFocus -> if (hasFocus) viewModel.onFocus(2) }
        editText3.onFocusChangeListener= View.OnFocusChangeListener { v, hasFocus -> if (hasFocus) viewModel.onFocus(3) }
        editText4.onFocusChangeListener= View.OnFocusChangeListener { v, hasFocus -> if (hasFocus) viewModel.onFocus(4) }
        editText5.onFocusChangeListener= View.OnFocusChangeListener { v, hasFocus -> if (hasFocus) viewModel.onFocus(5) }
    }
}
