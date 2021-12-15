package com.example.modul10

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ShowMap : AppCompatActivity(),OnMapReadyCallback {
    private lateinit var fusedLocation: FusedLocationProviderClient
    private lateinit var currentloc : Location
    private var permissionCode =101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_map)
        fusedLocation = LocationServices.getFusedLocationProviderClient(this)
        fetchloc()
    }

    private fun fetchloc() {

                if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat
                        .checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                ){
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
                    return
                }
        var task = fusedLocation.lastLocation
        task.addOnSuccessListener {location->
            if(location!= null){
                currentloc =location
                Toast.makeText(this,currentloc.latitude.toString()+""+currentloc.longitude.toString(), Toast.LENGTH_SHORT).show()
                val supportMapFragment=(supportFragmentManager.findFragmentById(R.id.Mymap)as SupportMapFragment?)!!
                supportMapFragment.getMapAsync(this)

            }
        }
    }

    override fun onMapReady(googlemap: GoogleMap) {
        val latLng= LatLng(currentloc.latitude, currentloc.longitude)
        val makeOption = MarkerOptions().position(latLng).title("hello im there")
        googlemap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googlemap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,5f))
//        googlemap?.addMarker(makeOption)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            permissionCode-> if (grantResults.isEmpty() && grantResults[0]==
                    PackageManager.PERMISSION_GRANTED){
                fetchloc()
            }
        }
    }
}