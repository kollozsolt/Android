package com.example.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.quiz.databinding.ActivityMainBinding
import com.example.quiz.fragment.QuizStartFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val supportFragment: FragmentManager = supportFragmentManager;
        supportFragment.beginTransaction().add(R.id.fragmentContainerView, QuizStartFragment()).commit()
    }
}