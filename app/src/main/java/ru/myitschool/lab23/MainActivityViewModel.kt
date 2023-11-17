package ru.myitschool.lab23

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val repository = MetricsData()

    private val _metrics = MutableLiveData<List<Double>>()
    val metrics: LiveData<List<Double>>
        get() = _metrics

    fun recalculate(index: Int, value: String) {
        var count = 0.0
        try {
            count = value.toDouble()
        } catch (_: Exception) {
        }
        _metrics.value = repository.recalculate(index, count)
    }
}