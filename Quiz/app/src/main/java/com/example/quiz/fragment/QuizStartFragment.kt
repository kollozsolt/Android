package com.example.quiz.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.example.quiz.R
import com.example.quiz.SharedViewModel
import com.example.quiz.databinding.FragmentQuizStartBinding

class QuizStartFragment : Fragment() {

    private var _binding: FragmentQuizStartBinding? = null
    private val binding get() = _binding!!

    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuizStartBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private lateinit var nameEditText: EditText;
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var clickButton: Button = binding.startButton
        var chooseButton: Button = binding.chooseContactButton
        nameEditText = binding.nameTextInput

        clickButton.setOnClickListener {
            val name = nameEditText.text.toString()
            if (name != "") {
                model.setName(name)
                Toast.makeText(
                    activity,
                    "Let the fun begin, $name!",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("MainActivity", "Starting the quiz")
                val supportFragment: FragmentManager? = activity?.supportFragmentManager
                supportFragment?.beginTransaction()?.replace(R.id.fragmentContainerView, QuestionFragment())?.commit()
            } else {
                Toast.makeText(
                    activity,
                    "Please enter your name!",
                    Toast.LENGTH_LONG
                ).show()

                Log.d("MainActivity", "Missing the name")
            }
        }

        chooseButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI).also { it.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE }
            getContent.launch(intent)
        }
    }


    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

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
                    activity?.contentResolver?.query(contactUri, projection, null, null, null)?.apply {
                        moveToFirst()
                        nameEditText.text = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)).toEditable()
                        close()
                    }
                }
            }
        }
}