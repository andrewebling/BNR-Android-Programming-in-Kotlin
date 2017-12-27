package com.bignerdranch.android.geoquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    lateinit var mTrueButton: Button
    lateinit var mFalseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        mTrueButton = findViewById<Button>(R.id.true_button)
        mTrueButton.setOnClickListener {

            val toast = Toast.makeText(this,
                    R.string.correct_toast,
                    Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        }

        mFalseButton = findViewById<Button>(R.id.false_button)
        mFalseButton.setOnClickListener {

            println("false clicked")
        }
    }
}
