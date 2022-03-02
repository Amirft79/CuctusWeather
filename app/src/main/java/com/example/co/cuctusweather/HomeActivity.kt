package com.example.co.cuctusweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.co.cuctusweather.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding

    private lateinit var Listener:NavController.OnDestinationChangedListener


    private lateinit var navController:NavController
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var appbarConfig:AppBarConfiguration
    private lateinit var navHost:NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.navigteToollbar)
        navHost=supportFragmentManager.findFragmentById(R.id.fragment_container_view)
                as NavHostFragment
        navController=navHost.navController
        drawerLayout=binding.appDrawer
        binding.navigationView.setupWithNavController(navController)
        appbarConfig= AppBarConfiguration(navController.graph,drawerLayout)
        setupActionBarWithNavController(navController,appbarConfig)
        Listener=NavController.OnDestinationChangedListener { controller, destination, arguments ->

        }
        binding.bottomNavView.setupWithNavController(navController)
    }

    override fun onPause() {
        super.onPause()
        navController.addOnDestinationChangedListener(Listener)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(Listener)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=findNavController(R.id.fragment_container_view)
        return navController.navigateUp(appbarConfig)||super.onSupportNavigateUp()
    }
}