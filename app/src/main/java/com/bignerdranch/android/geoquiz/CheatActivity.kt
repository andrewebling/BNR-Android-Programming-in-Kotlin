package com.bignerdranch.android.geoquiz

import android.content.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cheat.*

class CheatActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"

        fun newIntent(context: Context, answerIsTrue: Boolean): Intent {
            val intent = Intent(context, CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            return intent
        }
    }

    private var mAnswerIsTrue: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        mAnswerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        requireNotNull(mAnswerIsTrue) { "No answer is true extra provided"}

        showAnswerButton.setOnClickListener {
            val answerId = if(mAnswerIsTrue)  R.string.true_button else R.string.false_button
            answerTextView.setText(getString(answerId))
        }
    }
}
