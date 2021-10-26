package com.example.quiz

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quiz.fragment.QuestionFragment

class SharedViewModel: ViewModel() {
    private var name = "USER"
    private var score = 0
    private var index = 0

    private lateinit var questions: QuizController

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String = this.name

    fun addScore():Any = this.score++

    fun getScore(): Int = this.score

    fun getIndex(): Int = this.index

    fun getQuestions() = this.questions.getQuestions()

    fun makeQuizController(context: Context){
        questions = QuizController(context)
    }

    fun addIndex() {
        if (index == 9){
            index = 0
        } else {
            index ++
        }
    }

    fun zero(){
        this.score = 0
    }

}