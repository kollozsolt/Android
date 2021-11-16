package com.example.quiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.quiz.R
import com.example.quiz.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var startButton: Button
    private lateinit var newQuestionButton: Button
    private lateinit var listButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startButton = binding.homeButton1
        listButton = binding.homeButton2
        newQuestionButton = binding.homeButton3

        startButton.setOnClickListener {
            val supportFragment: FragmentManager? = activity?.supportFragmentManager
            supportFragment?.beginTransaction()?.replace(R.id.fragmentContainerView, QuizStartFragment())?.commit()
        }

        listButton.setOnClickListener {
            val supportFragment: FragmentManager? = activity?.supportFragmentManager
            supportFragment?.beginTransaction()?.replace(R.id.fragmentContainerView, ListFragment())?.commit()
        }

        newQuestionButton.setOnClickListener{
            val supportFragment: FragmentManager? = activity?.supportFragmentManager
            supportFragment?.beginTransaction()?.replace(R.id.fragmentContainerView, QuestionAddFragment())?.commit()
        }
    }
}