package com.example.sharedprefslearning

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var cardView: CardView
    private lateinit var prefManager: PreferenceManager
    private var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        cardView = findViewById(R.id.cardView)
        val buttonIncrement: MaterialButton = findViewById(R.id.buttonIncrement)
        val buttonDecrement: MaterialButton = findViewById(R.id.buttonDecrement)
        prefManager = PreferenceManager(this)

        number = prefManager.getNumber()?.toInt() ?: 0
        updateDisplay(number, animate = false)

        buttonIncrement.setOnClickListener {
            updateNumber(number + 1)
        }

        buttonDecrement.setOnClickListener {
            updateNumber(number - 1)
        }
    }

    private fun updateNumber(newNumber: Int) {
        val oldNumber = number
        number = newNumber
        prefManager.saveNewNumber(number.toString())
        updateDisplay(oldNumber, number)
    }

    private fun updateDisplay(startValue: Int, endValue: Int = startValue, animate: Boolean = true) {
        if (animate) {
            val textAnimator = ValueAnimator.ofInt(startValue, endValue)
            textAnimator.duration = 500
            textAnimator.interpolator = AccelerateDecelerateInterpolator()
            textAnimator.addUpdateListener { animation ->
                textView.text = animation.animatedValue.toString()
            }

            val isIncreasing = endValue > startValue
            val scaleStart = 1f
            val scaleMiddle = if (isIncreasing) 1.1f else 0.9f
            val scaleEnd = 1f

            val scaleX = ObjectAnimator.ofFloat(cardView, "scaleX", scaleStart, scaleMiddle)
            val scaleY = ObjectAnimator.ofFloat(cardView, "scaleY", scaleStart, scaleMiddle)
            val scaleBackX = ObjectAnimator.ofFloat(cardView, "scaleX", scaleMiddle, scaleEnd)
            val scaleBackY = ObjectAnimator.ofFloat(cardView, "scaleY", scaleMiddle, scaleEnd)

            val animatorSet = AnimatorSet()
            animatorSet.play(textAnimator).with(scaleX).with(scaleY)
            animatorSet.play(scaleBackX).with(scaleBackY).after(scaleX)

            animatorSet.duration = 500
            animatorSet.start()
        } else {
            textView.text = endValue.toString()
        }
    }
}