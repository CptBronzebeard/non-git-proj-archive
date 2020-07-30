package com.ilin.simplecurrencyconverter.ui.conversion

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilin.simplecurrencyconverter.database.CurrencyPair
import com.ilin.simplecurrencyconverter.database.CurrencyQueueAdapter
import com.ilin.simplecurrencyconverter.model.Currency
import com.ilin.simplecurrencyconverter.util.CurrencyJsonParser
import com.ilin.simplecurrencyconverter.util.HttpRequestBuilder
import com.ilin.simplecurrencyconverter.util.getUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ConversionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    lateinit var currencyQueue: CurrencyQueueAdapter
    fun createQueue(appContext: Context) = CurrencyQueueAdapter.getInstance(appContext)
    suspend fun insertAndGet(pair: CurrencyPair): List<CurrencyPair> {
        currencyQueue.insert(pair)
        return currencyQueue.getAll()
    }

    suspend fun getHistory() = currencyQueue.getAll()
    val currencyList: MutableLiveData<List<Currency>?> by lazy { MutableLiveData<List<Currency>?>() }
    fun getCurrencies(): List<Currency> {
        return currencyList.value!!
    }

    fun updateRates(calendar: Calendar) {
        var response: String
        val queryDate = calendar.clone() as Calendar
        do {
            response = HttpRequestBuilder.getRequest(getUrl(queryDate))
            queryDate.add(Calendar.DAY_OF_MONTH, -1)
        } while (response == "")
        GlobalScope.launch(Dispatchers.Main) {
            currencyList.value = CurrencyJsonParser.parseCurrencies(response)
        }
    }

    val text: LiveData<String> = _text
    val calendar = MutableLiveData<Calendar>()
    var posFrom: Int = 0
    var posTo: Int = 0

}