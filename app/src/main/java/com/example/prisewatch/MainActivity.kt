package com.example.prisewatch

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
/*
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
       // fab = binding.appBarMain.fab

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
   *//*     appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home_drawer_item
            ), drawerLayout
        )*//*
       // setupActionBarWithNavController(navController, appBarConfiguration)
        val bottomBar = binding.appBarMain.bottomBar
        bottomBar.setupWithNavController(navController)
      //  navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }*/
}