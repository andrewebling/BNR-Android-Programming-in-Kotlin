package com.bignerdranch.android.geoquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    private lateinit var mTrueButton: Button
    private lateinit var mPrevButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mNextButton: Button
    private lateinit var mQuestionTextView: TextView

    private val mQuestionBank = arrayOf(
            Question(R.string.question_australia, false),
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_asia, true))
    private var mCurrentQuestionIndex = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        mQuestionTextView = findViewById<TextView>(R.id.question_text_view)
        mQuestionTextView.setOnClickListener {
            progressQuestion(true)
        }
        updateQuestion()

        mTrueButton = findViewById<Button>(R.id.true_button)
        mTrueButton.setOnClickListener {

                checkAnswer(true)
        }

        mFalseButton = findViewById<Button>(R.id.false_button)
        mFalseButton.setOnClickListener {

            checkAnswer(false)
        }

        mPrevButton = findViewById<Button>(R.id.prev_button)
        mPrevButton.setOnClickListener {
            progressQuestion(false)
        }

        mNextButton = findViewById<Button>(R.id.next_button)
        mNextButton.setOnClickListener {

            progressQuestion(true)
        }
    }

    private fun progressQuestion(forward: Boolean) {

        val increment: Int

        if(forward) {
            increment = 1
        } else {
            if (mCurrentQuestionIndex == 0) {
                increment = mQuestionBank.size - 1
            } else {
                increment = -1
            }
        }
        mCurrentQuestionIndex = (mCurrentQuestionIndex + increment) % mQuestionBank.size
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionId = mQuestionBank[mCurrentQuestionIndex].mTextResId
        mQuestionTextView.setText(questionId)
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerTrue = mQuestionBank[mCurrentQuestionIndex].mAnswerTrue
        val messageResId: Int

        if (userPressedTrue == answerTrue) {
            messageResId = R.string.correct_toast
        } else {
            messageResId = R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}
