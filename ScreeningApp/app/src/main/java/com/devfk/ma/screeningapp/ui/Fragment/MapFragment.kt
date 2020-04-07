package com.devfk.ma.screeningapp.ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.devfk.ma.screeningapp.R
import com.devfk.ma.screeningapp.data.Model.Event
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : Fragment() , OnMapReadyCallback {
   private var list: ArrayList<Event> = ArrayList()
    var carouselView: CarouselView? = null

    private lateinit var mMap: GoogleMap
    companion object {

        @JvmStatic
        fun newInstance(eventList: ArrayList<Event>) = MapFragment().apply {
            arguments = Bundle().apply {
                list.addAll(eventList)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_map, container, false)
        carouselView = view.findViewById<CarouselView>(R.id.carouselView)
        val mapFragment = childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initialization()

        return view
    }

    private fun initialization() {
//        Toast.makeText(context,list.size.toString(),Toast.LENGTH_LONG).show()
        carouselView?.setPageCount(list.size)
        carouselView?.setImageListener(imageListener)


    }

    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            imageView.setImageResource(list[position].image)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val first = LatLng(list[0].lat.toDouble(), list[0].long.toDouble())
        for (i in list){
            mMap.addMarker(MarkerOptions()
                .position(LatLng(i.lat.toDouble(), i.long.toDouble()))
                .title(i.name))
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(first, 15f))

        carouselView?.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val move = LatLng(list[position].lat.toDouble(), list[position].long.toDouble())
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(move, 17f))
            }

            override fun onPageSelected(position: Int) {
                //Update your layout here
            }
        })
    }

}
