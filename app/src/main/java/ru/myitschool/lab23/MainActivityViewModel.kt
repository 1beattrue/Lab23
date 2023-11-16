package ru.myitschool.lab23

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val repository = MetricsData()

    private val _metrics = MutableStateFlow(List(25) {0.0})
    val metrics: StateFlow<List<Double>>
        get() = _metrics

    fun recalculate(index: Int, value: String) {
        val count = if (value.isBlank()) 0.0 else value.toDouble()
        _metrics.value = repository.recalculate(index, count)
    }
}