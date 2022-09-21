package com.example.prisewatch

import android.icu.util.TimeZone
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.prisewatch.databinding.ActivityMain2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.sql.Time
import java.time.Duration

class MainActivity2 : AppCompatActivity() {
    private lateinit var fab: FloatingActionButton
    private lateinit var bottom: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        fab = binding.fab
        navController = findNavController(R.id.nav_host_fragment)
        bottom = binding.bottomBar
        bottom.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.d("TAG", "dest ${destination.id}")
            when (destination.id) {
                R.id.homeFragment -> {
                    if (bottom.visibility == View.GONE) bottom.visibility = View.VISIBLE
                    if (fab.visibility == View.GONE) fab.show()
                }
                R.id.settingsFragment -> {
                    if (bottom.visibility == View.GONE) bottom.visibility = View.VISIBLE
                    if (fab.visibility == View.GONE) fab.show()
                }
                R.id.itemDetailFragment -> {
                    bottom.visibility = View.GONE
                    fab.hide()
                }
            }
        }

        fab.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                Log.d("TAG", "dwdwdwdwdwdw")
                Snackbar.make(fab, "hdwjkdhkwd", Snackbar.LENGTH_LONG).show()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}