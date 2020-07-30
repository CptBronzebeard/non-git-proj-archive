package com.ilin.simplecurrencyconverter.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency_pairs")
    fun getAll(): List<CurrencyPair>

    @Query("SELECT * FROM currency_pairs ORDER BY uid LIMIT 1")
    fun getFirst(): CurrencyPair

    @Insert
    fun insert(pair: CurrencyPair)

    @Delete
    fun delete(pair: CurrencyPair)
}