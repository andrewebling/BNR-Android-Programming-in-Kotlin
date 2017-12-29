package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cheat.*

class CheatActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"
        const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"

        const val INDEX_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_true"
        const val INDEX_USER_CHEATED = "com.bignerdranch.android.geoquiz.user_cheated"

        fun newIntent(context: Context, answerIsTrue: Boolean): Intent {
            val intent = Intent(context, CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            return intent
        }

        fun wasAnswerShown(result: Intent): Boolean {
            return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
        }
    }

    private var mAnswerIsTrue = false
    private var mUserCheated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        if(savedInstanceState != null) {
            mAnswerIsTrue = savedInstanceState.getBoolean(INDEX_ANSWER_IS_TRUE, false)
            setAnswerShownResult(savedInstanceState.getBoolean(INDEX_USER_CHEATED, false))
        }

        mAnswerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        requireNotNull(mAnswerIsTrue) { "No answer is true extra provided"}

        showAnswerButton.setOnClickListener {
            val answerId = if(mAnswerIsTrue)  R.string.true_button else R.string.false_button
            answerTextView.setText(getString(answerId))
            setAnswerShownResult(true)
            mUserCheated = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(INDEX_ANSWER_IS_TRUE, mAnswerIsTrue)
        outState?.putBoolean(INDEX_USER_CHEATED, mUserCheated)
    }

    private fun setAnswerShownResult(answerWasShown: Boolean) {
        val data = Intent()
        data.putExtra(EXTRA_ANSWER_SHOWN, answerWasShown)
        setResult(Activity.RESULT_OK, data)
    }
}
