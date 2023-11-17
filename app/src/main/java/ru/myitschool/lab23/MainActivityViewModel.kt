package ru.myitschool.lab23

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

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