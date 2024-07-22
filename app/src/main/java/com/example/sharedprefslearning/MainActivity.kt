package com.example.sharedprefslearning

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var prefManager: PreferenceManager
    private var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        val buttonIncrement: Button = findViewById(R.id.buttonIncrement)
        val buttonDecrement: Button = findViewById(R.id.buttonDecrement)
        prefManager = PreferenceManager(this)

        number = prefManager.getNumber()?.toInt() ?: 0
        updateDisplay()

        buttonIncrement.setOnClickListener {
            number++
            prefManager.saveNewNumber(number.toString())
            updateDisplay()
        }

        buttonDecrement.setOnClickListener {
            number--
            prefManager.saveNewNumber(number.toString())
            updateDisplay()
        }
    }

    private fun updateDisplay() {
        textView.text = number.toString()
    }
}