package com.ilin.simplecurrencyconverter.database

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.*

class Converters {
    @TypeConverter
    fun calendarToTimestamp(calendar: Calendar): Long {
        return calendar.timeInMillis
    }

    @TypeConverter
    fun fromTimestamp(value: Long): Calendar {
        val cal = Calendar.getInstance()
        cal.timeInMillis = value
        return cal
    }

    @TypeConverter
    fun bigDecimalToString(value: BigDecimal) = value.toString()

    @TypeConverter
    fun fromString(value: String) = value.toBigDecimal()
}