package ru.myitschool.lab23

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val repository = MetricsData()

    private val _metrics = MutableSharedFlow<List<Double>>(1)
    val metrics: Flow<List<Double>>
        get() = _metrics

    fun recalculate(index: Int, value: String) {
        viewModelScope.launch {
            _metrics.emit(repository.recalculate(index, value))
        }
    }
}