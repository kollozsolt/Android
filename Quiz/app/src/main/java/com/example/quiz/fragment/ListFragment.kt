package com.example.quiz.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.*
import com.example.quiz.databinding.FragmentListBinding
import com.example.quiz.databinding.FragmentQuestionBinding
import java.io.InputStream

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionsList = getQuestions(requireContext())

        val exampleList = mutableListOf<ExampleItem>()
        var poz = 0
        for (i in questionsList){
            for (j in 0..(i.answers.size-1)){
                if (i.answers[j].isCorrect == 1){
                    poz = j
                }
            }
            val rightAnswer = i.answers[poz].text;
            exampleList.add(ExampleItem(i.text, "Single answer", rightAnswer))
        }
        val recycleView: RecyclerView = binding.recyclerView

        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.setHasFixedSize(true)
        recycleView.adapter = ExampleAdapter(exampleList)
    }

    fun getQuestions(context: Context) : MutableList<Question> {
        val questions = mutableListOf<Question>()
        val lines = mutableListOf<String>()

        val inputStream: InputStream? = context?.resources?.openRawResource(R.raw.questions)
        inputStream?.bufferedReader()?.useLines { lines_ -> lines_.forEach { lines.add(it) } }
        Log.d("SharedViewModel", "INIT")
        for (i in 0..lines.size - 1 step 5) {
            var text = lines[i]
            var answers = mutableListOf<Answer>()
            for (j in i + 1..i + 4) {
                var index = lines[j].indexOf('/')
                var text = lines[j].substring(0, index)
                var correct = lines[j].substring(index + 1, lines[j].length)
                var answer = Answer(text, correct.toInt())
                answers.add(answer)
            }
            var question = Question(text, answers)
            questions.add(question)
        }
        return questions
    }
}