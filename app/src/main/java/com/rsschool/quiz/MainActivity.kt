package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), QuizFragment.OnQuizFragmentSendData, ResultFragment.OnResultFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchQuizFragment(0, IntArray(DataBase.questionList.size))
    }

    private fun launchQuizFragment(fragmentNumber: Int,userAnswers: IntArray) {
        val fragment = QuizFragment.newInstance(fragmentNumber,userAnswers)
        supportFragmentManager.beginTransaction()
            .replace(R.id.quiz_container, fragment)
            .commit()
    }

    private fun launchResultFragment() {
        val fragment = ResultFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.quiz_container, fragment)
            .commit()
    }

    override fun replaceFragment(fragmentNumber: Int, userAnswers: IntArray) {
        launchQuizFragment(fragmentNumber,userAnswers)
    }

    override fun resultFragment() {
        launchResultFragment()
    }

    override fun restartQuiz() {
        launchQuizFragment(0,IntArray(DataBase.questionList.size))
    }


}