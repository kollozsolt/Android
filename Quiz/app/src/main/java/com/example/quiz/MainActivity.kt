package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var clickButton: Button = findViewById(R.id.startButton)
        val nameEditText: EditText = findViewById(R.id.nameTextInput)

        clickButton.setOnClickListener {
            val name = nameEditText.text
            if (name.toString() != "") {
                Toast.makeText(
                    applicationContext,
                    "Let the fun begin, $name!",
                    Toast.LENGTH_SHORT
                ).show()

                Snackbar.make(
                    findViewById(R.id.main), // Parent view
                    "Let the fun begin, $name!", // Message to show
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).setAction("OK", {}).show()

                Log.d("MainActivity", "Starting the quiz")
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