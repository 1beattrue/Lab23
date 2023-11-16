package ru.myitschool.lab23

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel : ViewModel() {
    private val repository = MetricsData()

    private val _metrics = MutableStateFlow(List(25) {0.0})
    val metrics: StateFlow<List<Double>>
        get() = _metrics

    private val _nowEditingIndex = MutableLiveData<Int>()
    val nowEditingIndex: LiveData<Int>
        get() = _nowEditingIndex

    fun recalculate(index: Int, value: String) {
        _nowEditingIndex.value = index
        val count = if (value.isBlank()) 0.0 else value.toDouble()
        _metrics.value = repository.recalculate(index, count)
    }
}