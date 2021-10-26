package com.example.quiz

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.quiz.fragment.QuestionFragment
import java.io.InputStream

class QuizController(context: Context){
    private val questions = mutableListOf<Question>()
    private val lines = mutableListOf<String>()

    init {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.questions)
        inputStream.bufferedReader().useLines { lines_ -> lines_.forEach { lines.add(it) } }
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
        questions.shuffle()
    }

    fun getQuestions() = this.questions

}