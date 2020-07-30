package com.ilin.simplecurrencyconverter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ilin.simplecurrencyconverter.model.Currency
import java.math.BigDecimal
import java.util.*

@Entity(tableName = "currency_pairs")
@TypeConverters(Converters::class)
data class CurrencyPair(
    @ColumnInfo(name = "short_name_from") val charCodeFrom: String,
    @ColumnInfo(name = "name_from") val nameFrom: String,
    @ColumnInfo(name = "amount_from") val amountFrom: BigDecimal,
    @ColumnInfo(name = "short_name_to") val charCodeTo: String,
    @ColumnInfo(name = "name_to") val nameTo: String,
    @ColumnInfo(name = "amount_to") val amountTo: BigDecimal,
    @ColumnInfo(name = "date") val date: Calendar
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    constructor(from: Currency, to: Currency, amount: BigDecimal, date: Calendar) :
            this(
                charCodeFrom = from.charCode,
                nameFrom = from.name,
                charCodeTo = to.charCode,
                nameTo = to.name,
                amountFrom = amount,
                amountTo = from.convert(to, amount),
                date = date
            )
}