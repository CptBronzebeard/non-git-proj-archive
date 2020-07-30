package com.ilin.simplecurrencyconverter

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.ilin.simplecurrencyconverter.ui.conversion.ConversionFragment
import com.ilin.simplecurrencyconverter.ui.history.HistoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: CustomViewPager
    private lateinit var adapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

//Remove notification bar
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.navigation_dashboard -> {
                    viewPager.currentItem = 1
                    true
                }
                else -> false
            }
        }
        /*val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)*/

        //button.setOnClickListener({viewPager.setCurrentItem(0)})
        //button1.setOnClickListener({viewPager.setCurrentItem(1)})
        viewPager = findViewById(R.id.nav_host_fragment)
        adapter = ViewPagerAdapter(this.supportFragmentManager)
        adapter.addFragment(ConversionFragment(), "title")
        adapter.addFragment(HistoryFragment(), "title")
        viewPager.adapter = adapter

    }
}
