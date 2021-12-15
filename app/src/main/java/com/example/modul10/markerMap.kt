package com.example.modul10

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.modul10.databinding.ActivityMarkerMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.Marker

class markerMap : AppCompatActivity(), OnMapReadyCallback ,GoogleMap.OnMarkerClickListener{

    private lateinit var mMap: GoogleMap
    private lateinit var lastlocation:Location
    private lateinit var  fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding: ActivityMarkerMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMarkerMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setupMap()
    }

    private fun setupMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mMap.isMyLocationEnabled=true
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {location->
            if (location!=null){
                lastlocation=location
                val currentlocation = LatLng(lastlocation.latitude, lastlocation.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentlocation,12f))
                placeMarkerOnMap(currentlocation)
            }
        }
    }

    private fun placeMarkerOnMap(currentlocation: LatLng) {
        val markerOptions = MarkerOptions().position(currentlocation)
        markerOptions.title("$currentlocation")
        mMap.addMarker(markerOptions)

    }

    override fun onMarkerClick(p0: Marker):Boolean{
        AlertDialog.Builder(this)
            .setTitle("Hello")
            .setMessage("hi im in indonesia ")
            .setPositiveButton("close") { dialogInterface, which -> dialogInterface.dismiss() }
            .show()
        return false
    }


}


