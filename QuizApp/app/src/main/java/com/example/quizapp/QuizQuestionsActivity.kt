package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class QuizQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        val questionList = Constants.getQuestions()

        val currentPosition = 1
        val question: Question? = questionList[currentPosition - 1]
        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = currentPosition

        var tv_progress = findViewById<TextView>(R.id.tv_progress)
        tv_progress.text = "$currentPosition" + "/" + progressBar.max

        var tv_question = findViewById<TextView>(R.id.tv_question)
        tv_question.text = question!!.question

        var iv_image = findViewById<ImageView>(R.id.iv_image)
        iv_image.setImageResource(question.image)

        var tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        var tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        var tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        var tv_option_four = findViewById<TextView>(R.id.tv_option_four)

        tv_option_one.text = question.option_one
        tv_option_two.text = question.option_two
        tv_option_three.text = question.option_three
        tv_option_four.text = question.option_four

    }
}