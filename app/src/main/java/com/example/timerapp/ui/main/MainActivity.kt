package com.example.timerapp.ui.main

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.timerapp.R
import com.example.timerapp.extensions.popBackStackAndNavigate
import com.example.timerapp.helpers.SessionManager
import com.example.timerapp.ui.signin.SignInActivity
import com.example.timerapp.ui.timer.TimerFragment
import com.example.timerapp.utils.AppUtil
import com.google.android.material.navigation.NavigationView
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.alert

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout =  findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.sidenav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment? ?: return

        // Set up Action Bar
        navController = host.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.TimerFragment -> {drawerLayout.close()}
            }
        }
        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.TimerFragment -> {
                    navController.popBackStackAndNavigate(
                        R.id.TimerFragment,
                        null,
                        AppUtil.getNavOptions()
                    )
                    drawerLayout.close()
                }
                R.id.UsersFragment -> {
                    navController.popBackStackAndNavigate(
                        R.id.UsersFragment,
                        null,
                        AppUtil.getNavOptions()
                    )
                    drawerLayout.close()
                }
                R.id.nav_logout -> {
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("Confirmation")
                        .setMessage("Are you sure you want to logout?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(
                            android.R.string.yes
                        ) { dialog, whichButton ->
                            SessionManager.rememberSignedIn = false
                            SessionManager.userTime = 0
                            SessionManager.userLoginDate = ""
                            intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        .setNegativeButton(android.R.string.no, null).show()

                }
            }
            true
        }
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.TimerFragment) {
            alert(getString(R.string.are_you_sure_to_exit)) {
                positiveButton(getString(R.string.yes)) {
                    finish()
                    super.onBackPressed()
                }
                neutralPressed(getString(R.string.no)) {}
            }.show()
        }else{
            navController.navigate(
                R.id.TimerFragment,
                null,
                AppUtil.getNavOptions()
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // Allows NavigationUI to support proper up navigation or the drawer layout
        // drawer menu, depending on the situation
        return findNavController(R.id.home_nav_host_fragment).navigateUp(appBarConfiguration)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}