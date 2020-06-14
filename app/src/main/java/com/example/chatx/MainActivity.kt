package com.example.chatx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity(){

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    var firebaseUser: FirebaseUser? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseUser = FirebaseAuth.getInstance().currentUser

        if (firebaseUser == null) {
            setContentView(R.layout.welcome_activity)
        } else {
            setContentView(R.layout.activity_main)

            toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)

            drawerLayout = findViewById(R.id.drawer_layout)
            navView = findViewById(R.id.nav_view)

            val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, 0, 0
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun loginBtnNavHeaderClicked(view: View){
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
    }

    fun addChannelBtnClicked(view: View){

    }

    fun sendMessageClicked(view: View){

    }

    fun welcomeLoginBtnClicked(view: View){
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
    }

    fun welcomeSignUpBtnClicked(view: View){
        val createUserIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createUserIntent)
    }
}
