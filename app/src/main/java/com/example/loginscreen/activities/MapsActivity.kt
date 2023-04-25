package com.example.loginscreen.activities

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.location.LocationRequest
import com.example.loginscreen.R

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.UiSettings
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker

internal class MapsActivity : AppCompatActivity(), OnMapReadyCallback ,GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var currentLocation: Location
    private val permissionCode = 101
    private var mapfragment: SupportMapFragment? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    companion object {
        const val LOCATION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

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
        val Latlon1 = LatLng(36.8046392, 10.1735334)
        val Latlon2 = LatLng(36.8046392, 10.1735334)
        val Latlon3 = LatLng(36.8098001, 10.1533264)
        val Latlon4 = LatLng(36.8418162, 10.1494641)
        val Latlon5 = LatLng(36.79444, 10.1714796)
        val Latlon6 = LatLng(36.7930653, 10.1760287)
        val Latlon7 = LatLng(36.8090785, 10.1704926)
        val markerView = (getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.marker_layout,
            null
        )
        val text = markerView.findViewById<TextView>(R.id.markerText)
        val cardView = markerView.findViewById<CardView>(R.id.markercardView)
        text.text = "M1"
        val bitmap1 = Bitmap.createScaledBitmap(viewToBitmap(cardView)!!,cardView.width,cardView.height,false)
        val smallMarkerIcon1=BitmapDescriptorFactory.fromBitmap(bitmap1)
        googleMap.addMarker(MarkerOptions().position(Latlon1).icon(smallMarkerIcon1))

        text.text = "M2"
        val bitmap2 = Bitmap.createScaledBitmap(viewToBitmap(cardView)!!,cardView.width,cardView.height,false)
        val smallMarkerIcon2=BitmapDescriptorFactory.fromBitmap(bitmap1)
        googleMap.addMarker(MarkerOptions().position(Latlon2).icon(smallMarkerIcon2))

        text.text = "M3"
        val bitmap3 = Bitmap.createScaledBitmap(viewToBitmap(cardView)!!,cardView.width,cardView.height,false)
        val smallMarkerIcon3=BitmapDescriptorFactory.fromBitmap(bitmap3)
        googleMap.addMarker(MarkerOptions().position(Latlon3).icon(smallMarkerIcon3))

        text.text = "M4"
        val bitmap4 = Bitmap.createScaledBitmap(viewToBitmap(cardView)!!,cardView.width,cardView.height,false)
        val smallMarkerIcon4=BitmapDescriptorFactory.fromBitmap(bitmap4)
        googleMap.addMarker(MarkerOptions().position(Latlon1).icon(smallMarkerIcon4))

        text.text = "M5"
        val bitmap5 = Bitmap.createScaledBitmap(viewToBitmap(cardView)!!,cardView.width,cardView.height,false)
        val smallMarkerIcon5=BitmapDescriptorFactory.fromBitmap(bitmap5)
        googleMap.addMarker(MarkerOptions().position(Latlon5).icon(smallMarkerIcon5))

        text.text = "M6"
        val bitmap6 = Bitmap.createScaledBitmap(viewToBitmap(cardView)!!,cardView.width,cardView.height,false)
        val smallMarkerIcon6=BitmapDescriptorFactory.fromBitmap(bitmap6)
        googleMap.addMarker(MarkerOptions().position(Latlon6).icon(smallMarkerIcon6))

        text.text = "M7"
        val bitmap7 = Bitmap.createScaledBitmap(viewToBitmap(cardView)!!,cardView.width,cardView.height,false)
        val smallMarkerIcon7=BitmapDescriptorFactory.fromBitmap(bitmap7)
        googleMap.addMarker(MarkerOptions().position(Latlon7).icon(smallMarkerIcon7))


        mMap.setOnMarkerClickListener {
            val newFragment = Driverdialogfragment.newInstance("pass content here")

            val fm = supportFragmentManager
            newFragment.show(fm, "look")
            true
        }


    }
    private fun viewToBitmap(view:View):Bitmap?{
        view.measure(View.MeasureSpec.UNSPECIFIED ,View.MeasureSpec.UNSPECIFIED)
        val bitmap=Bitmap.createBitmap(view.measuredWidth,view.measuredHeight,Bitmap.Config.ARGB_8888)
        val canvas=Canvas(bitmap)
        view.layout(0,0,view.measuredWidth,view.measuredWidth)
        view.draw(canvas)
        return bitmap

    }

      private fun setUpMap(){
          if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED ){

              ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                  LOCATION_REQUEST_CODE)
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

