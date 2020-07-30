package com.ilin.simplecurrencyconverter.util

import com.ilin.simplecurrencyconverter.model.Currency
import org.json.JSONObject
import java.math.BigDecimal

class CurrencyJsonParser {
    companion object {
        fun parseCurrencies(response: String): List<Currency>? {
            val obj = JSONObject(response)
            val obj1 = obj.optJSONObject("Valute")
            val jsonObjList = obj1?.toJSONArray(obj1.names())
                ?.let { 0.until(it.length()).map { i -> it.optJSONObject(i) } } // returns an array of JSONObject

            var currencyList = jsonObjList?.map { Currency(it.toString()) }
            currencyList = currencyList?.let {
                it + Currency(
                    "RUB",
                    BigDecimal.valueOf(1),
                    1,
                    "Российский Рубль"
                )
            }
            return currencyList
        }
    }
}