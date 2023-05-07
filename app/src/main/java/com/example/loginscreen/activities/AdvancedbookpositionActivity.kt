package com.example.loginscreen.activities

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import com.example.loginscreen.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.example.loginscreen.databinding.ActivityAdvancedbookposition2Binding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.Marker
import java.util.Locale

class AdvancedbookpositionActivity : AppCompatActivity(), OnMapReadyCallback ,GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var currentLocation: Location
    private val permissionCode = 101
    private var mapfragment: SupportMapFragment? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var binding: ActivityAdvancedbookposition2Binding

    companion object {
        private const val LOCATION_REQUEST_CODE = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdvancedbookposition2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setUpMap()

        // Add a marker in Sydney and move the camera
       /* val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
        val latLng=LatLng(currentLocation?.latitude!!,currentLocation?.longitude!!)
        drawMarker(latLng)

        val markerView = (getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.marker_layout,
            null
        )
        mMap.setOnMarkerClickListener {
            val newFragment = Driverdialogfragment.newInstance("pass content here")

            val fm = supportFragmentManager
            newFragment.show(fm, "look")
            true
        }


    }
    private fun drawMarker(latLng: LatLng){/*
        MarkerOptions().position(latLng).title("Im here").snippet(getAddress(latLng.latitude,latLng.latitude))*/
        MarkerOptions().position(latLng)
    }
 /*   private fun getAddress(Lat: Double,lon :Double):String?{
       val geocoder= Geocoder(this,Locale.getDefault())
      val addresses=  geocoder.getFromLocation(Lat ,lon,1)
       return [0].getAddressLine(0).toString()
    }*/
    private fun setUpMap(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED ){

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MapsActivity.LOCATION_REQUEST_CODE
            )
            return
        }
        mMap.isMyLocationEnabled=true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null){
                lastLocation=location
                val currentLatLong =LatLng(location.latitude,location.longitude)
                placeMarkerOnMap(currentLatLong)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong,12f))
            }
        }

    }
    private fun placeMarkerOnMap(currentLatLong: LatLng){
        val markerOptions =MarkerOptions().position(currentLatLong)
        markerOptions.title("$currentLatLong")
        mMap.addMarker(markerOptions)
    }


    override fun onMarkerClick(p0: Marker)=false


}


class MyClass {
    companion object {
        const val MY_VARIABLE_1: Int = 42
        const val MY_VARIABLE_2: String = "Hello World"
    }
}

