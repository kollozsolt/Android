package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var clickButton: Button = findViewById(R.id.startButton)
        val nameEditText: EditText = findViewById(R.id.nameTextInput)

        clickButton.setOnClickListener {
            val name = nameEditText.text.toString()
            if (name != "") {
                Toast.makeText(
                    applicationContext,
                    "Let the fun begin, $name!",
                    Toast.LENGTH_SHORT
                ).show()

                Log.d("MainActivity", "Starting the quiz")

                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, name)
                }
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Please enter your name!",
                    Toast.LENGTH_LONG
                ).show()

                Snackbar.make(
                    findViewById(R.id.main), // Parent view
                    "Please enter your name!", // Message to show
                    Snackbar.LENGTH_INDEFINITE // How long to display the message.
                ).setAction("OK", {}).show()

                Log.d("MainActivity", "Missing the name")
            }
        }
    }
}