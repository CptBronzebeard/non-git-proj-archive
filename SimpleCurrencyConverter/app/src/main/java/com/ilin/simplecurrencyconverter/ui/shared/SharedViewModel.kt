package com.ilin.simplecurrencyconverter.ui.shared

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilin.simplecurrencyconverter.database.CurrencyPair

class SharedViewModel : ViewModel() {
    var conversionHistory = MutableLiveData<List<CurrencyPair>>()
    fun updateHistory(list: List<CurrencyPair>) {
        conversionHistory.value = list
    }
}