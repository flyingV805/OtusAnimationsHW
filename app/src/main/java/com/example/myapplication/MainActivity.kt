package com.example.myapplication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator

class MainActivity : AppCompatActivity() {

    private val animLength = 300L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<android.view.View>(R.id.button)
        val button1 = findViewById<android.view.View>(R.id.button1)
        val button2 = findViewById<android.view.View>(R.id.button2)
        val button3 = findViewById<android.view.View>(R.id.button3)
        val button4 = findViewById<android.view.View>(R.id.button4)

        //va1.

        var delta = 0
        button.post {
            delta = (button.parent as ViewGroup).width - button.width
            Log.d("Anim", (delta).toString())
        }

        button.setOnClickListener {
            val firstAnimator = ValueAnimator.ofFloat(0f, delta.toFloat())
                .apply {
                    duration = animLength
                }
            firstAnimator
                .addUpdateListener {
                    Log.d("Anim", (it.animatedValue).toString())
                    button.translationX = it.animatedValue as Float
                }

            firstAnimator.start()

        }

        button1.setOnClickListener {
            ObjectAnimator.ofFloat(button1, "alpha", 1f, 0f).apply { interpolator = BounceInterpolator() }.start()
        }

        button2.setOnClickListener {

            val alphaAnimator = ValueAnimator.ofFloat(1f, 0f).apply {duration = animLength}
            alphaAnimator.addUpdateListener {
                Log.d("Anim", (it.animatedValue).toString())
                button2.alpha = it.animatedValue as Float
            }

            val translateAnimator = ValueAnimator.ofFloat(0f, delta.toFloat()).apply {duration = animLength}
            translateAnimator.addUpdateListener {
                Log.d("Anim", (it.animatedValue).toString())
                button2.translationX = it.animatedValue as Float
            }

            val animatorSet =  AnimatorSet()
            animatorSet.playTogether(alphaAnimator, translateAnimator)

            animatorSet.start()
        }

        button3.setOnClickListener {
            button3.animate().rotation(270f).apply {
                duration = animLength
                interpolator = BounceInterpolator()
            }.start()
        }

        button4.setOnClickListener {



        }

    }

}