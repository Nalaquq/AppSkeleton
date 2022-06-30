package com.example.mymaps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymaps.model.Place
import com.example.mymaps.model.UserMap

private const val TAG = "MainActivity"
const val EXTRA_USER_MAP = "EXTRA_USER_MAP"

class MainActivity : AppCompatActivity() {
    //rvMaps definition
    private lateinit var rvMaps:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        rvMaps = findViewById(R.id.rvMaps)

        val userMaps = generateSampleData()
        //Layout Manager Setup
        rvMaps.layoutManager = LinearLayoutManager(this)
        //Adapter on Recycler View Setup
        rvMaps.adapter = MapsAdapter(this, userMaps, object: MapsAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
                Log.i(TAG, "onItemClick $position")
                val intent = Intent(this@MainActivity, DisplayMapsActivity::class.java)
                intent.putExtra(EXTRA_USER_MAP, userMaps[position]) //Passes data (Locations) onto MapsActivity
                startActivity(intent)
            }

        })
    }

    private fun generateSampleData(): List<UserMap> {
        return listOf(
            UserMap(
                "Start Mission",
                listOf(
                    Place("Branner Hall", "Best dorm at Stanford", 37.426, -122.163),
                    Place("Gates CS building", "Many long nights in this basement", 37.430, -122.173),
                    Place("Pinkberry", "First date with my wife", 37.444, -122.170)
                )
            ),
            UserMap("Check Weather",
                listOf(
                    Place("Tokyo", "Overnight layover", 35.67, 139.65),
                    Place("Ranchi", "Family visit + wedding!", 23.34, 85.31),
                    Place("Singapore", "Inspired by \"Crazy Rich Asians\"", 1.35, 103.82)
                )),
            UserMap("View Results",
                listOf(
                    Place("Potential Match 1", "Wetland, Cold, Green", 62.846, -164.295),
                    Place("Potential Match 2", "River, Wetter, Blue", 62.715, -164.306),
                    Place("Potential Match 3", "Is anyone reading this?", 62.644, -163.686)
                )
            )
        )
    }
}