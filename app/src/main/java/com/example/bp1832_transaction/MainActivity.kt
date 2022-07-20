package com.example.bp1832_transaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate( savedInstanceState: Bundle?)  {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        // Hide Title Bar
        getSupportActionBar()?.hide()

        // Instance
        val bottomNav:BottomNavigationView = findViewById( R.id.bottomNavigationView ) ;

        // Set Fragment
        val accountFragment     = FragmentProfile()
        val makananFragment     = FragmentMakanan()
        val transactionFragment = com.example.bp1832_transaction.FragmentTransaction()
        val reportFragment      = FragmentReport();
        val menuFragment        = FragmentMenu();

        // Default Fragment
        supportFragmentManager.beginTransaction().apply {
            replace( R.id.fragment_container, menuFragment )
            commit()
        }

        currentFragment( menuFragment )

        // Floating Bottom Menu
        val fab: View = findViewById( R.id.fab )
        fab.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace( R.id.fragment_container, menuFragment )
                commit()
            }
        }

        bottomNav.setOnNavigationItemSelectedListener {
            when( it.itemId ){
                R.id.fragmentProfile     -> currentFragment( accountFragment )
                R.id.fragmentMakanan     -> currentFragment( makananFragment )
                R.id.fragmentTransaction -> currentFragment( transactionFragment )
                R.id.fragmentReport      -> currentFragment( reportFragment )

            }
            true
        }
    }

    private fun currentFragment( fragment: Fragment ) =
        supportFragmentManager.beginTransaction().apply {
            replace( R.id.fragment_container, fragment )
            commit()
        }

}