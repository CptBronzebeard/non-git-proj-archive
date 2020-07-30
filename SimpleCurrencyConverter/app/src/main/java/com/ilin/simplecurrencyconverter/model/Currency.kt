package com.ilin.simplecurrencyconverter.model

import com.ilin.simplecurrencyconverter.util.Constants
import com.ilin.simplecurrencyconverter.util.bdiv
import org.json.JSONObject
import java.math.BigDecimal

data class Currency(
    var charCode: String = "",
    var value: BigDecimal = BigDecimal("0.0"),
    var nominal: Int = 0,
    var name: String = ""
) {
    /*    var charCode:String
        var value:BigDecimal
        var nominal:Int
        var name:String*/
    constructor(json: String) : this() {
        val tmp = JSONObject(json)
        charCode = tmp.optString("CharCode")
        value = BigDecimal(tmp.optString("Value"), Constants.MCONTEXT)
        nominal = tmp.optInt("Nominal")
        name = tmp.optString("Name")
    }
    /*constructor(charCode:String, value:BigDecimal, nominal:Int, name:String) {
        this.charCode = charCode
        this.value = value
        this.nominal = nominal
        this.name = name
    }*/

    override fun toString(): String {
        return charCode
    }

    fun convert(other: Currency, amount: BigDecimal): BigDecimal {
        return (value * amount * other.nominal.toBigDecimal()).bdiv(other.value).bdiv(nominal.toBigDecimal())
    }
}