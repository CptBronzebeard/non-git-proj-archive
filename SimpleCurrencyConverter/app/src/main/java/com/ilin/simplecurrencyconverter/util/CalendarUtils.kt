package com.ilin.simplecurrencyconverter.util

import android.text.Editable
import java.text.SimpleDateFormat
import java.util.*

var Calendar.year: Int
    get() = this.get(Calendar.YEAR)
    set(inp) = this.set(Calendar.YEAR, inp)
var Calendar.month: Int
    get() = this.get(Calendar.MONTH)
    set(inp: Int) = this.set(Calendar.MONTH, inp)
var Calendar.day: Int
    get() = this.get(Calendar.DAY_OF_MONTH)
    set(inp: Int) = this.set(Calendar.DAY_OF_MONTH, inp)

fun Calendar.toEditable(): Editable =
    Editable.Factory.getInstance().newEditable(formatDate())//""+this.year + "/" + this.month+"/"+this.day)

fun Calendar.toFormattedString(): String = formatDate()//""+this.year + "/" + this.month+"/"+this.day
fun Calendar.formatDate(): String = SimpleDateFormat("yyyy/MM/dd").format(this.time)
fun getUrl(calendar: Calendar) =
    "https://www.cbr-xml-daily.ru/archive/" + calendar.toFormattedString() + "/daily_json.js"
