package ru.myitschool.lab23

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val repository = MetricsData()

    private val _metrics = MutableSharedFlow<List<Double>>(1)
    val metrics = _metrics.asSharedFlow()

    fun recalculate(index: Int, value: String) {
        viewModelScope.launch {
            _metrics.emit(repository.recalculate(index, value))
        }
    }
}