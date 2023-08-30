package com.example.touchtocount.viewmodel

import androidx.lifecycle.ViewModel
import com.example.touchtocount.model.CounterModel

class CounterViewModel: ViewModel() {
    private val counterModel = CounterModel()

    fun getCount(): Int {
        return counterModel.count
    }

    fun incrementCount() {
        counterModel.count++
    }

    fun resetCount() {
        counterModel.count = 0
    }

    fun loadCount(cnt: Int) {
        counterModel.count = cnt
    }
}