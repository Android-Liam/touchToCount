package com.example.touchtocount.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.touchtocount.R
import com.example.touchtocount.model.DatabaseHelper
import com.example.touchtocount.viewmodel.CounterViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var counterViewModel: CounterViewModel
    private lateinit var cntView: TextView
    private lateinit var resetView: TextView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cntView = findViewById<TextView>(R.id.cntView)
        resetView = findViewById<TextView>(R.id.resetView)

        counterViewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

        databaseHelper = DatabaseHelper(this)

        cntView.setOnClickListener {
            counterViewModel.incrementCount()
            val newCount = counterViewModel.getCount()
            updateCountText(newCount)
            saveCountToDatabase(newCount)
        }

        updateCountText(counterViewModel.getCount())

        resetView.setOnClickListener {
            resetCountText()
        }
    }

    private fun resetCountText() {
        counterViewModel.resetCount()
        updateCountText(counterViewModel.getCount())
    }

    private fun saveCountToDatabase(count: Int) {
        databaseHelper.insertCount(count)
    }

    private fun updateCountText(count: Int) {
        cntView.text = count.toString()
    }


}