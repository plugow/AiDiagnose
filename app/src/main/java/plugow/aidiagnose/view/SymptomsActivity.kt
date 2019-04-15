package plugow.aidiagnose.view

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_symptoms.*
import kotlinx.android.synthetic.main.search_bar.*
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivitySymptomsBinding
import plugow.aidiagnose.recycler.SymptomAdapter
import plugow.aidiagnose.recycler.SymptomListener
import plugow.aidiagnose.viewModel.SymptomsViewModel
import javax.inject.Inject

class SymptomsActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel:SymptomsViewModel
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivitySymptomsBinding>(this,R.layout.activity_symptoms)
        viewModel= ViewModelProviders.of(this, viewModelFactory)[SymptomsViewModel::class.java]
        binding.viewModel=viewModel
        recyclerView.setHasFixedSize(true)
        // use a linear layout manager
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mAdapter = SymptomAdapter( object : SymptomListener {
            override fun onSymptomClicked(pos: Int) {
                viewModel.onSymptomClicked(pos)
            }
        })
        recyclerView.adapter = mAdapter


        editText.onFocusChangeListener= View.OnFocusChangeListener { v, hasFocus -> if (hasFocus)viewModel.onFocus(1) }
        editText2.onFocusChangeListener= View.OnFocusChangeListener { v, hasFocus -> if (hasFocus) viewModel.onFocus(2) }
        editText3.onFocusChangeListener= View.OnFocusChangeListener { v, hasFocus -> if (hasFocus) viewModel.onFocus(3) }
        editText4.onFocusChangeListener= View.OnFocusChangeListener { v, hasFocus -> if (hasFocus) viewModel.onFocus(4) }
        editText5.onFocusChangeListener= View.OnFocusChangeListener { v, hasFocus -> if (hasFocus) viewModel.onFocus(5) }
    }
}
