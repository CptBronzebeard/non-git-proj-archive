package com.ilin.simplecurrencyconverter.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyPair::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}