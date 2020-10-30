package com.aprogrammer.howfar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), InternetConnectionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        (this@MainActivity.application as App).setInternetConnectionListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_about ->{
                Snackbar.make(window.decorView.findViewById(android.R.id.content),
                    "Developed by: Dinesh Solanki",Snackbar.LENGTH_SHORT).show()
                true}

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onInternetUnavailable() {
      //TODO()
    }
    override fun onPause() {
        super.onPause()
        (application as App).removeInternetConnectionListener()
    }
}