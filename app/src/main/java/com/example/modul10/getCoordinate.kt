package com.example.modul10

import android.content.pm.PackageManager


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices



class getCoordinate : AppCompatActivity() {
    lateinit var fusedLocationProvider: FusedLocationProviderClient
    lateinit var txt1:TextView
    lateinit var txt2:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_coordinate2)
        val btnGetLoc :Button = findViewById(R.id.btnloc)
         txt1 = findViewById(R.id.lat)
         txt2 = findViewById(R.id.longitutde)
        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)


        btnGetLoc.setOnClickListener{
            fetchlocation()
        }
    }

    private fun fetchlocation() {
        val task = fusedLocationProvider.lastLocation
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=
            PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
        ){
           ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
            return
        }

        task.addOnSuccessListener {
            if(it != null){
                Toast.makeText(applicationContext,"${it.latitude} ${it.longitude}",Toast.LENGTH_SHORT).show()
                txt1.setText("latitude : ${it.latitude}")
                txt2.setText("longitude : ${it.longitude}")

            }
        }

    }
}