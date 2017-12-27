package com.bignerdranch.android.geoquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mQuestionTextView: TextView

    private val mQuestionBank = arrayOf(
            Question(R.string.question_australia, true),
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_asia, true))
    private var mCurrentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        mQuestionTextView = findViewById<TextView>(R.id.question_text_view)
        val questionId = mQuestionBank[mCurrentQuestionIndex].mTextResId
        mQuestionTextView.setText(questionId)

        mTrueButton = findViewById<Button>(R.id.true_button)
        mTrueButton.setOnClickListener {

                Toast.makeText(this,
                        R.string.correct_toast,
                        Toast.LENGTH_SHORT).show()
        }

        mFalseButton = findViewById<Button>(R.id.false_button)
        mFalseButton.setOnClickListener {

            println("false clicked")
        }
    }
}
