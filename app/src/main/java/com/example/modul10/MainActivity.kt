package com.example.modul10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number1 :Button = findViewById(R.id.no1)
        var number2 :Button = findViewById(R.id.no2)
        var number3 :Button = findViewById(R.id.no3)

        number1.setOnClickListener{
            val Intent =Intent(this,getCoordinate::class.java)
            startActivity(Intent)
        }

        number2.setOnClickListener{
            val Intent =Intent(this,ShowMap::class.java)
            startActivity(Intent)
        }

        number3.setOnClickListener{
            val Intent =Intent(this,markerMap::class.java)
            startActivity(Intent)
        }
    }
}