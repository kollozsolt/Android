package com.example.quiz

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.example.quiz.databinding.ActivityMainBinding
import com.example.quiz.fragment.HomeFragment
import com.example.quiz.fragment.QuizStartFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    val supportFragment: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout : DrawerLayout = binding.drawerLayout
        val navView : NavigationView = binding.navView

        supportFragment.beginTransaction().add(R.id.fragmentContainerView, HomeFragment()).commit()

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    supportFragment.beginTransaction()
                        .replace(R.id.fragmentContainerView, HomeFragment()).commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_quiz_time -> {
                    supportFragment.beginTransaction()
                        .replace(R.id.fragmentContainerView, QuizStartFragment()).commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_profile -> {
                    Toast.makeText(applicationContext, "Clicked Profile", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_list -> {
                    Toast.makeText(applicationContext, "Clicked List", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_new_question -> {
                    Toast.makeText(applicationContext, "Clicked New Question", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}