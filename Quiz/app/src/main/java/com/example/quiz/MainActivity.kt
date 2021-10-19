package com.example.quiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.provider.ContactsContract
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val contactUri = result.data?.data
                if (contactUri != null) {
                    val projection = arrayOf(
                        ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    this.contentResolver?.query(contactUri, projection, null, null, null)?.apply {
                        moveToFirst()
                        nameEditText.text = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)).toEditable()
                        close()
                    }
                }
            }
        }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    private lateinit var nameEditText: EditText;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var clickButton: Button = findViewById(R.id.startButton)
        var chooseButton: Button = findViewById(R.id.chooseContactButton)
        nameEditText= findViewById(R.id.nameTextInput)

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

        chooseButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI).also { it.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE }
            getContent.launch(intent)
        }
    }
}