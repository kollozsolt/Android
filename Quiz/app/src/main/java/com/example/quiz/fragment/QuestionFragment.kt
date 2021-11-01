package com.example.quiz.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.example.quiz.Question
import com.example.quiz.R
import com.example.quiz.SharedViewModel
import com.example.quiz.databinding.FragmentQuestionBinding


class QuestionFragment : Fragment() {

    private val model: SharedViewModel by activityViewModels()

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private lateinit var button: Button
    private lateinit var text: TextView
    private lateinit var questions: MutableList<Question>
    private lateinit var geek1: RadioButton
    private lateinit var geek2: RadioButton
    private lateinit var geek3: RadioButton
    private lateinit var geek4: RadioButton
    private lateinit var rg: RadioGroup
    private var checkedId = -1
    var rightAnswer = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        geek1 = RadioButton(context)
        geek2 = RadioButton(context)
        geek3 = RadioButton(context)
        geek4 = RadioButton(context)
        button = binding.nextButton
        text = binding.questionTextView
        model.makeQuizController(requireContext())
        questions = model.getQuestions()

        rg = binding.radioGroup
        addRadiobuttons(rg)

        text.setText(questions[0].text)
        button.setOnClickListener { refresh() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showAreYouSureDialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    fun showAreYouSureDialog(){
        Log.d("KAKA", "Pressed")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Exit")
        builder.setMessage("Are you sure you want to end this quiz?")

        builder.setPositiveButton("Yes") { dialog, which ->
            val supportFragment: FragmentManager? = activity?.supportFragmentManager
            supportFragment?.beginTransaction()?.replace(R.id.fragmentContainerView, QuizEndFragment())?.commit()
        }

        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(context, "Continue", Toast.LENGTH_SHORT).show()
        }

        builder.show()
    }

    private fun isRight(index: Int) {
        checkedId = rg.checkedRadioButtonId
        questions[index].answers.forEach {
            if (it.isCorrect == 1) rightAnswer = questions[index].answers.indexOf(it)
        }
        if (checkedId == rightAnswer) {
            model.addScore()
        }
    }

    private fun refresh() {
        var index = model.getIndex()
        isRight(index)
        Log.d("Score", "${model.getScore()}")

        if (index == 9) {
            val supportFragment: FragmentManager? = activity?.supportFragmentManager
            supportFragment?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, QuizEndFragment())?.commit()
        }

        model.addIndex()
        index = model.getIndex()
        text.setText(questions[index].text)
        questions[index].answers.shuffle()
        geek1.setText(questions[index].answers[0].text)
        geek2.setText(questions[index].answers[1].text)
        geek3.setText(questions[index].answers[2].text)
        geek4.setText(questions[index].answers[3].text)
        geek1.setChecked(false)
        geek2.setChecked(false)
        geek3.setChecked(false)
        geek4.setChecked(false)
        rg.clearCheck()

        if (index == 9) {
            button.setText("FINISH!")
        }
    }

    @SuppressLint("ResourceType")
    private fun addRadiobuttons(rg: RadioGroup) {
        geek1.layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        geek1.id = 0
        geek1.setText(questions[0].answers[0].text)
        geek2.layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        geek2.id = 1
        geek2.setText(questions[0].answers[1].text)
        geek3.layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        geek3.id = 2
        geek3.setText(questions[0].answers[2].text)
        geek4.layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        geek4.id = 3
        geek4.setText(questions[0].answers[3].text)
        rg.addView(geek1)
        rg.addView(geek2)
        rg.addView(geek3)
        rg.addView(geek4)
    }
}