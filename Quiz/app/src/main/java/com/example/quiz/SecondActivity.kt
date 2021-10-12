package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d("SecondActivity", "Proba")
        val name = intent.getStringExtra(EXTRA_MESSAGE)
        findViewById<TextView>(R.id.nameTextView).apply { text = name }
    }
}