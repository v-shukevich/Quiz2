package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchQuizFragment()
    }

    private fun launchQuizFragment(){
        val fragment = QuizFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.quiz_container, fragment)
            .commit()
    }


}