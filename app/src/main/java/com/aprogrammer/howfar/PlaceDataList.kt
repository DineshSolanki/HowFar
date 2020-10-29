package com.aprogrammer.howfar

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class PlaceDataList : AppCompatActivity() {
    private var listView: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_data_list)
        listView = findViewById(R.id.listView)
        //val currentPlace = intent.getStringExtra("currentPlace")
        val placeModelArrayList: ArrayList<Place> =
            intent.getParcelableArrayListExtra("locations")!!
        val placeAdapter = PlaceAdapter(this, placeModelArrayList)
        listView!!.adapter = placeAdapter
    }

}