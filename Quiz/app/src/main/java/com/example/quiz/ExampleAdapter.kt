package com.example.quiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.InputStream

class ExampleAdapter(private val exampleList: List<ExampleItem>) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExampleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

        return ExampleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = exampleList.size


    class ExampleViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private val questionView: TextView = view.findViewById(R.id.question_TextView)
        private val answerType: TextView = view.findViewById(R.id.answerType_textView)
        private val rightAnswer: TextView = view.findViewById(R.id.rightAnswer_textView)

        fun bind(exampleItem: ExampleItem){
            questionView.text = exampleItem.question
            answerType.text = exampleItem.answerType
            rightAnswer.text = exampleItem.rightAnswer
        }

    }
}