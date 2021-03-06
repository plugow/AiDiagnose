package plugow.aidiagnose.view

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_maps.*
import org.jetbrains.anko.startActivity
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivityMapsBinding
import plugow.aidiagnose.model.Doctor
import plugow.aidiagnose.view.dialogFragment.MapBottomSheetFragment
import plugow.aidiagnose.viewModel.MapViewModel
import javax.inject.Inject

class MapsActivity : DaggerAppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var markers:MutableList<MarkerOptions>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityMapsBinding>(this,R.layout.activity_maps)
        val viewModel= ViewModelProviders.of(this, viewModelFactory)[MapViewModel::class.java]
        binding.viewModel=viewModel
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        doctorImageView.setOnClickListener {
            val bottomSheetFragment=MapBottomSheetFragment.newInstance()
            bottomSheetFragment.show(supportFragmentManager,"bottom sheet")
        }
        symptomQuestionTextView.setOnClickListener { startActivity<SymptomsActivity>() }

        viewModel.getDoctorList().observe(this, androidx.lifecycle.Observer<List<Doctor>> { doctors ->
            mMap.clear()
            markers= mutableListOf()
            doctors?.forEach { doc ->
                val pos=LatLng(doc.longitude,doc.latitude)
                mMap.addMarker(MarkerOptions().position(pos).title(doc.firstName).icon(BitmapDescriptorFactory.fromResource(R.drawable.laska)))
            }

         })
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val krakow = LatLng(50.067599, 19.974942)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(krakow, 14.5.toFloat()))
    }
}
