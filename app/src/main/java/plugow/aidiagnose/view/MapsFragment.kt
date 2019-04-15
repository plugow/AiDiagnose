package plugow.aidiagnose.view

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.FragmentMapsBinding
import plugow.aidiagnose.model.Doctor
import plugow.aidiagnose.view.dialogFragment.MapBottomSheetFragment
import plugow.aidiagnose.view.dialogFragment.SendVisitBottomSheetFragment
import plugow.aidiagnose.viewModel.MapViewModel



class MapsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    override fun onInfoWindowClick(p0: Marker?) {
        val sendVisitFragment= SendVisitBottomSheetFragment.newInstance()
        sendVisitFragment.show(activity?.supportFragmentManager,"bottom sheet")
    }

    private var mMap: GoogleMap?=null
    lateinit var mapFragment: SupportMapFragment
    private val viewModel by lazy{
        ViewModelProviders.of(this).get(MapViewModel::class.java)
    }
    lateinit var markers:MutableList<MarkerOptions>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding= DataBindingUtil.inflate<FragmentMapsBinding>(inflater,R.layout.fragment_maps,null,false)
        binding.viewModel=viewModel

        mapFragment = SupportMapFragment.newInstance()
        mapFragment.getMapAsync(this)
        val transaction = childFragmentManager.beginTransaction()
        // R.id.map is a layout
        transaction.replace(R.id.map, mapFragment).commit()


        val rootView = binding.root
        return rootView
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap!!.uiSettings.isRotateGesturesEnabled = false
        val krakow = LatLng(50.067599, 19.974942)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(krakow, 14.5.toFloat()))
        mMap!!.setOnInfoWindowClickListener(this)

        viewModel.getDoctorList().observe(this, androidx.lifecycle.Observer<List<Doctor>> { doctors ->
            mMap!!.clear()
            markers= mutableListOf()
            doctors?.forEach { doc ->
                val pos=LatLng(doc.longitude,doc.latitude)
                mMap!!.addMarker(MarkerOptions().position(pos).title(doc.firstName).icon(BitmapDescriptorFactory.fromResource(R.drawable.laska)))
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doctorImageView.setOnClickListener {
            val bottomSheetFragment= MapBottomSheetFragment.newInstance()
            bottomSheetFragment.show(activity?.supportFragmentManager,"bottom sheet")
        }
        symptomQuestionTextView.setOnClickListener { context?.startActivity<SymptomsActivity>() }


    }


}
