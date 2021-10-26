package com.example.quiz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.example.quiz.R
import com.example.quiz.SharedViewModel
import com.example.quiz.databinding.FragmentQuizEndBinding
import com.example.quiz.databinding.FragmentQuizStartBinding

class QuizEndFragment : Fragment() {
    private var _binding: FragmentQuizEndBinding? = null
    private val binding get() = _binding!!

    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuizEndBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val score = model.getScore()
        val text:TextView = binding.scoreTextView
        val button:Button = binding.tryAgainButton
        text.setText("10/$score")
        button.setOnClickListener{ tryAgain() }
    }

    private fun tryAgain(){
        model.zero()
        val supportFragment: FragmentManager? = activity?.supportFragmentManager
        supportFragment?.beginTransaction()?.replace(R.id.fragmentContainerView, QuestionFragment())?.commit()
    }

}