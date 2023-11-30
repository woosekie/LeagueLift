package com.example.leaguelift.ui
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class DateViewModel : ViewModel() {

    private val _formattedDate = MutableLiveData<String>()
    val formattedDate: LiveData<String>
        get() = _formattedDate

    init {
        // Set initial date when ViewModel is created
        updateFormattedDate()
    }

    fun updateFormattedDate() {
        val currentDate = getCurrentDate()
        val formattedDate = formatDate(currentDate)
        _formattedDate.value = formattedDate
    }

    private fun getCurrentDate(): Date {
        return Calendar.getInstance().time
    }

    private fun formatDate(date: Date): String {
        val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
        return sdf.format(date)
    }
}