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
        val button: Button = findViewById(R.id.button)
        prefManager = PreferenceManager(this)

        number = prefManager.getNumber()?.toInt() ?: 0
        updateDisplay()

        button.setOnClickListener {
            number++
            prefManager.saveNewNumber(number.toString())
            updateDisplay()
        }
    }

    private fun updateDisplay() {
        textView.text = number.toString()
    }
}