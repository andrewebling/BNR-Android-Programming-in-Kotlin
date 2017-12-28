package com.bignerdranch.android.geoquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    companion object {
        const val TAG = "QuizActivity"
        const val KEY_INDEX = "index"
    }

    private lateinit var mTrueButton: Button
    private lateinit var mPrevButton: ImageButton
    private lateinit var mFalseButton: Button
    private lateinit var mNextButton: ImageButton
    private lateinit var mQuestionTextView: TextView

    private val mQuestionBank = arrayOf(
            Question(R.string.question_australia, false),
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_asia, true))
    private var mCurrentQuestionIndex = 0
    private var mCorrectAnswers = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        if(savedInstanceState != null) {
            mCurrentQuestionIndex = savedInstanceState.getInt(KEY_INDEX, 0)
        }

        mQuestionTextView = findViewById<TextView>(R.id.question_text_view)
        mQuestionTextView.setOnClickListener {
            progressQuestion(true)
        }


        mTrueButton = findViewById<Button>(R.id.true_button)
        mTrueButton.setOnClickListener {

            checkAnswer(true)
            mQuestionBank[mCurrentQuestionIndex].answered = true
            updateQuestion()
        }

        mFalseButton = findViewById<Button>(R.id.false_button)
        mFalseButton.setOnClickListener {

            checkAnswer(false)
            mQuestionBank[mCurrentQuestionIndex].answered = true
            updateQuestion()
        }

        mPrevButton = findViewById<ImageButton>(R.id.prev_button)
        mPrevButton.setOnClickListener {
            progressQuestion(false)
        }

        mNextButton = findViewById<ImageButton>(R.id.next_button)
        mNextButton.setOnClickListener {

            progressQuestion(true)
        }

        updateQuestion()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState?.putInt(KEY_INDEX, mCurrentQuestionIndex)
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

        val answered = mQuestionBank[mCurrentQuestionIndex].answered
        mFalseButton.isEnabled = !answered
        mTrueButton.isEnabled = !answered
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerTrue = mQuestionBank[mCurrentQuestionIndex].mAnswerTrue
        val messageResId: Int

        if (userPressedTrue == answerTrue) {
            messageResId = R.string.correct_toast
            mCorrectAnswers = mCorrectAnswers + 1
        } else {
            messageResId = R.string.incorrect_toast
        }

        if(allQuestionsAnswered()) {
            val msg = getString(R.string.quiz_complete) + " " + "%.2f".format(score()) + "%"
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        }
    }

    private fun allQuestionsAnswered(): Boolean {
        return mQuestionBank.filter { !(it.answered) }.size == 1
    }

    private fun score():Float {
        return (mCorrectAnswers / mQuestionBank.size.toFloat()) * 100
    }
}
